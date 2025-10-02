package org.excercise.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialOffer {

    // fields

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate specialOfferDateBegin;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate specialOfferDateEnd;

    private String title;



    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;


    // getters
    public Integer getId() {
        return id;
    }

    public LocalDate getSpecialOfferDateBegin() {
        return specialOfferDateBegin;
    }

    public LocalDate getSpecialOfferDateEnd() {
        return specialOfferDateEnd;
    }

    public String getTitle() {
        return title;
    }
    


    public Pizza getPizza() {
        return pizza;
    }



    // setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setSpecialOfferDateBegin(LocalDate specialOfferDateBegin) {
        this.specialOfferDateBegin = specialOfferDateBegin;
    }

    public void setSpecialOfferDateEnd(LocalDate specialOfferDateEnd) {
        this.specialOfferDateEnd = specialOfferDateEnd;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    
}
