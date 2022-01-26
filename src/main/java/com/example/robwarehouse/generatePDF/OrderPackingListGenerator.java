package com.example.robwarehouse.generatePDF;

import com.example.robwarehouse.model.OrderItem;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class OrderPackingListGenerator {
    private List<OrderItem> packinglist;


    public OrderPackingListGenerator(List<OrderItem> list){
        this.packinglist = list;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Position", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table){

        for (OrderItem orderItem : packinglist){
            table.addCell(String.valueOf(orderItem.getId()));
            table.addCell(String.valueOf(orderItem.getProduct().getName()));
            table.addCell(String.valueOf(orderItem.getQuantity()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Packing List", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.5f,3.5f,3.0f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
