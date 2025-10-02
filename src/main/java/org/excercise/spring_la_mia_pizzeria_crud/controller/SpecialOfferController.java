package org.excercise.spring_la_mia_pizzeria_crud.controller;

import org.excercise.spring_la_mia_pizzeria_crud.model.SpecialOffer;
import org.excercise.spring_la_mia_pizzeria_crud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/specialOffers")
public class SpecialOfferController {

    @Autowired
    private SpecialOfferRepository repository;

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOffer,
            BindingResult bindingResult) {
        
        if (bindingResult.hasErrors()) {
            return "specialOffers/edit";
        }
        repository.save(specialOffer);
        return "redirect:/pizzas/show/" + specialOffer.getPizza().getId();
    }



    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        SpecialOffer specialOffer = repository.findById(id).get();
        model.addAttribute("editMode", false);
        model.addAttribute("specialOffer", specialOffer);
        return "specialOffers/edit";
    }
    
    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("specialOffer") SpecialOffer specialOffer, BindingResult bindingResult,
            Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("editMode", true);
            return "specialOffers/edit";
        }
        repository.save(specialOffer);
        
        return "redirect:/pizzas/show/" + specialOffer.getPizza().getId();
    }
    
}
