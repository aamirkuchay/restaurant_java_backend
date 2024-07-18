package com.developer.zomato.entity;

import com.developer.zomato.dto.RestaurantDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String status;
    private Role role = Role.ROLE_CUSTOMER;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    private List<Order> orders =new ArrayList<>();


//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<RestaurantDto> favorites = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
     private List<Restaurant> favorites = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

}
