package com.developer.zomato.entity;

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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User customer;

    private Double total;
   @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CartItems> cartItems = new ArrayList<>();




}
