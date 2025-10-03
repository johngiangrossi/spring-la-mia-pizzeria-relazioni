package org.excercise.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "pizzas")
public class Pizza {



    // fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable=false)
    @NotBlank(message = "name cannot be blank, is mandatory")
    @NotNull(message = "name cannot be null, is mandatory")
    private String name;

    @Size(max = 255, message="description length max 255 char")
    private String description;

    @Email(message = "Must be a valid url")
    private String url;

    @Column(nullable=false)
    @DecimalMin("0.00")
    @NotNull(message = "price cannot be null, is mandatory")
    private BigDecimal price;


    @OneToMany(mappedBy = "pizza")
    private List<SpecialOffer> specialOffers;

    @ManyToMany
    @JoinTable(name = "pizza_ingredients", joinColumns = @JoinColumn(name = "pizza_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;


    // getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public String getUrl() {
        return url;
    }

    public BigDecimal getPrice() {
        return price;
    }
    
    public Integer getId() {
        return id;
    }
    

    public List<SpecialOffer> getSpecialOffers() {
        return specialOffers;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }



    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public void setSpecialOffers(List<SpecialOffer> specialOffers) {
        this.specialOffers = specialOffers;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }



}
