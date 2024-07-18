package com.developer.zomato.entity;

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
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
//    @JoinColumn(name = "food_id")
    private Food food;

    private int quantity;

    private Double totalPrice;

    private List<String> ingredients;
}
