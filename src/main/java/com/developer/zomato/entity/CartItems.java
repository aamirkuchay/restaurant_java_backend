package com.developer.zomato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
//    @JoinColumn(name = "food_id")
    private Food food;

    private int quantity;

    private List<String> ingredients;

    private double totalPrice;
}
