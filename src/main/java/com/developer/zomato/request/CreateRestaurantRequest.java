package com.developer.zomato.request;

import com.developer.zomato.entity.Address;
import com.developer.zomato.entity.Contact;
import lombok.Data;

import java.util.List;

@Data
public class CreateRestaurantRequest {
    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private Address address;
    private Contact contact;
    private String openingHours;
    private List<String> images;
}
