package com.example.robwarehouse.controller;

import com.example.robwarehouse.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import static com.example.robwarehouse.controller.RedirectUtil.redirect;

@RequiredArgsConstructor
public abstract class AbstractCrudController<T> {

    public static final String NEW_OBJECT = "newObject";
    public  static final String EDIT_OBJECT = "editObject";
    private final String baseUrl;
    private final CrudService<T> crudService;

    @GetMapping("/create")
    public String create (Model model){
       model.addAttribute(NEW_OBJECT,crudService.newObject());
       return baseUrl + "/" + "create";

    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute(NEW_OBJECT)T t){
       return RedirectUtil.redirect(baseUrl,crudService.create(t));

    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute(EDIT_OBJECT, crudService.get(id));
        return baseUrl + "/" + "edit";
    }

    @PostMapping("/edit/{id}")
    public String editLocation(@PathVariable Long id, @Valid @ModelAttribute(EDIT_OBJECT) T t) {
        crudService.update(t);
        return RedirectUtil.redirect(baseUrl + "/list");
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        crudService.delete(id);
        return redirect(baseUrl + "/list");
    }

    @GetMapping("/{id}")
    public String getLocation(@PathVariable Long id, Model model) {
        model.addAttribute("object", crudService.get(id));
        return baseUrl + "/view";
    }

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("objects", this.crudService.getAll());
        return baseUrl + "/list";
    }


}
