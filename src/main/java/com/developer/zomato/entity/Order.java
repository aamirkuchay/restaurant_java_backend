package com.developer.zomato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "customer_id")
    private User customer;

    @JsonIgnore
    @ManyToOne
//    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private Double totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
//    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @OneToMany
    private List<OrderItem> items;

//    private Payment payment;

    private int totalItem;
    private Double totalPrice;

}
