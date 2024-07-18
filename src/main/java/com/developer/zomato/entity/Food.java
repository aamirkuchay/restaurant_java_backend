package com.developer.zomato.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private  String description;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "food_category_id")
    private Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private boolean isVegetarian;
    private boolean isNonVegetarian;
    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredientsItems = new ArrayList<>();

    private Date creationDate;


}
