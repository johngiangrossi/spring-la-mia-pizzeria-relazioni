package org.excercise.spring_la_mia_pizzeria_crud.controller;

import org.excercise.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.excercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.excercise.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
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

import jakarta.validation.Valid;


@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientRepository repository;

    @GetMapping()
    public String index(Model model) {

        model.addAttribute("list", repository.findAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }



    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("ingredientObj") Ingredient ingredient,
            BindingResult bindingResult, Model model) {

        Ingredient ing = repository.findByIngredient(ingredient.getIngredient());
        if (ing != null) {
            bindingResult.addError(new ObjectError("ingredient", "Ingredient already present"));
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("list", repository.findAll());
            return "ingredients/index";
        }
        repository.save(ingredient);
        return "redirect:/ingredients";
    }
    

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        Ingredient ing = repository.findById(id).get();
        for(Pizza pizza : ing.getPizzas()) {
            pizza.getIngredients().remove(ing);
        }
        repository.deleteById(id);
        return "redirect:/ingredients";
    }
    

}
