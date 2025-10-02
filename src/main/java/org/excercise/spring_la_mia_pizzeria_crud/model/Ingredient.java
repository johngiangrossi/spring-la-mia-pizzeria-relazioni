package org.excercise.spring_la_mia_pizzeria_crud.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Ingredient {

    // fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String ingredient;

    @ManyToMany(mappedBy="ingredients")
    private List<Pizza> pizzas;

    // getters
    public Integer getId() {
        return id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }


    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    
}
