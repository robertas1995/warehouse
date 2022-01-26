package com.example.robwarehouse.service;

import com.example.robwarehouse.exception.PositionNotFoundException;
import com.example.robwarehouse.exception.PositionQuantityNotEnoughException;
import com.example.robwarehouse.model.Position;
import com.example.robwarehouse.repository.PositionRepo;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class PositionServiceImplTest {


    private static final Long PRODUCT_ID = 1L;
    private static final Integer NEEDED_QUANTITY = 50;
    private static final Long POSITION_ID = 10L;
    private static final Long POSITION_ID2 = 20L;

    @InjectMocks
    private PositionServiceImpl service;
    @Mock
    private PositionRepo positionRepo;

    @Captor
    private ArgumentCaptor<Position> positionArgumentCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void recalculatePositionsForProduct_whenProductNotFound_null() {
        Mockito.when(positionRepo.findByProductId(PRODUCT_ID)).thenReturn(null);
        Assertions.assertThrows(PositionNotFoundException.class, () -> service.recalculatePositionsForProduct(PRODUCT_ID, NEEDED_QUANTITY));
    }

    @Test
    void recalculatePositionsForProduct_whenProductNotFound_emptyList() {
        Mockito.when(positionRepo.findByProductId(PRODUCT_ID)).thenReturn(Collections.emptyList());
        Assertions.assertThrows(PositionNotFoundException.class, () -> service.recalculatePositionsForProduct(PRODUCT_ID, NEEDED_QUANTITY));
    }

    @Test
    void recalculatePositionsForProduct_whenPositionQuantityNotEnough_singlePosition() {
        Position position = new Position();
        position.setQuantity(5);
        Mockito.when(positionRepo.findByProductId(PRODUCT_ID)).thenReturn(List.of(position));
        Assertions.assertThrows(PositionQuantityNotEnoughException.class, () -> service.recalculatePositionsForProduct(PRODUCT_ID, NEEDED_QUANTITY));
    }

    @Test
    void recalculatePositionsForProduct_whenPositionQuantityNotEnough_multiplePositions() {
        Position position1 = new Position();
        position1.setQuantity(5);
        Position position2 = new Position();
        position2.setQuantity(20);
        Mockito.when(positionRepo.findByProductId(PRODUCT_ID)).thenReturn(List.of(position1, position2));
        Assertions.assertThrows(PositionQuantityNotEnoughException.class, () -> service.recalculatePositionsForProduct(PRODUCT_ID, NEEDED_QUANTITY));
    }

    @Test
    void recalculatePositionsForProduct_whenPositionQuantityEnough_singlePosition() {
        Position position = new Position();
        position.setId(POSITION_ID);
        position.setQuantity(55);
        Mockito.when(positionRepo.findByProductId(PRODUCT_ID)).thenReturn(List.of(position));
        Mockito.when(positionRepo.getById(POSITION_ID)).thenReturn(position);

        service.recalculatePositionsForProduct(PRODUCT_ID, NEEDED_QUANTITY);

        Mockito.verify(positionRepo).save(positionArgumentCaptor.capture());

        Assertions.assertEquals(5, positionArgumentCaptor.getValue().getQuantity());
    }

    @Test
    void recalculatePositionsForProduct_whenPositionQuantityEnough_multiplePosition() {
        Position position1 = new Position();
        position1.setId(POSITION_ID);
        position1.setQuantity(20);
        Position position2 = new Position();
        position2.setId(POSITION_ID2);
        position2.setQuantity(40);
        Position position3 = new Position();
        position3.setQuantity(40);
        Mockito.when(positionRepo.findByProductId(PRODUCT_ID)).thenReturn(List.of(position1, position2, position3));
        Mockito.when(positionRepo.getById(POSITION_ID)).thenReturn(position1);
        Mockito.when(positionRepo.getById(POSITION_ID2)).thenReturn(position2);

        service.recalculatePositionsForProduct(PRODUCT_ID, NEEDED_QUANTITY);

        Mockito.verify(positionRepo, Mockito.times(2)).save(positionArgumentCaptor.capture());

        List<Position> saved = positionArgumentCaptor.getAllValues();

        Assertions.assertEquals(2, saved.size());
        Assertions.assertEquals(0, saved.get(0).getQuantity());
        Assertions.assertEquals(10, saved.get(1).getQuantity());


    }

}
