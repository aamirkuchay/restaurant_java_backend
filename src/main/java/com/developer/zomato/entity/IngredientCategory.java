package com.developer.zomato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

//    @OneToMany(mappedBy = "ingredientCategory",cascade = CascadeType.ALL)
    @OneToMany
    private List<IngredientsItem> ingredientsItem = new ArrayList<>();
}
