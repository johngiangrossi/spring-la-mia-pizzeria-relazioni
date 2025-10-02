package org.excercise.spring_la_mia_pizzeria_crud.repository;

import java.util.List;
import java.util.Optional;

import org.excercise.spring_la_mia_pizzeria_crud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository extends JpaRepository<Pizza, Integer> {


    public List<Pizza> findByNameContainingIgnoreCase(String name);

    
    public Optional <Pizza> findByName(String name);

}
