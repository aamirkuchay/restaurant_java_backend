package com.developer.zomato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsItem {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private IngredientCategory ingredientCategory;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private boolean inStock= true;
}
