package com.developer.zomato.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private User owner;

    private String name;

    private String description;

    private String cuisineType;


    @ManyToOne
    private Address address;

    @Embedded
    private Contact contact;

    private String openingHours;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private LocalDateTime registrationDate;

    private boolean open;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Food> food = new ArrayList<>();
}
