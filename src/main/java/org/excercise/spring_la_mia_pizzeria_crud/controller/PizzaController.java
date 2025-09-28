package org.excercise.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.excercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.excercise.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;


    // index
    @GetMapping
    public String index(Model model, @RequestParam(name = "keyword", required = false) String keyword) {

        List<Pizza> result = null;
        if (keyword == null || keyword.isBlank()) {
            result = repository.findAll();
        } else {
            result = repository.findByNameContainingIgnoreCase(keyword);
        }
        model.addAttribute("list", result);
        return "pizzas/index";
    }
    


    // show
    @GetMapping("/show/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {

        Optional<Pizza> optionalPizza = repository.findById(id);
        if (optionalPizza.isPresent()) {
            model.addAttribute("pizza", optionalPizza.get());
            model.addAttribute("empty", false);
        } else {
            model.addAttribute("empty", true);
        }
        return "pizzas/show";
    }
    
    
    // create
    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    
    // create
    @PostMapping("/create")
    public String save(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        
        Optional<Pizza> optPizza = repository.findByName(formPizza.getName());
        if (optPizza.isPresent()) {
            bindingResult.addError(new ObjectError("name", "name already present"));
        }
        if (bindingResult.hasErrors()) {
            return "pizzas/create";
        }
        repository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "pizza created successfully");
        return "redirect:/pizzas";
    }
    
    
    // edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {

        Optional<Pizza> optPizza = repository.findById(id);
        Pizza pizza = optPizza.get();
        model.addAttribute("pizza", pizza);
        return "pizzas/edit";
    }
    
    
    // edit
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {

        Pizza oldPizza = repository.findById(formPizza.getId()).get();
        if (!oldPizza.getName().equals(formPizza.getName())) {
            bindingResult.addError(new ObjectError("name", "Cannot change the Name!"));
        }
        if (bindingResult.hasErrors()) {
            return "pizzas/edit";
        }     
        repository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza updated successfully");
        return "redirect:/pizzas";
    }
    
    
    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        
        repository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza eliminata con successo");
        return "redirect:/pizzas";
    }
    
    
}
