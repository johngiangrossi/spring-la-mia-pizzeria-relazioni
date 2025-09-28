package org.excercise.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "pizzas")
public class Pizza {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name cannot be blank, is mandatory")
    @NotNull(message = "name cannot be null, is mandatory")
    private String name;

    @Size(max = 255, message="description length max 255 char")
    private String description;

    @Pattern(regexp = "^(http|https)://.*$", message = "Must be a valid url")
    private String url;

    @DecimalMin("0.00")
    @NotNull(message = "price cannot be null, is mandatory")
    private BigDecimal price;


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
}
