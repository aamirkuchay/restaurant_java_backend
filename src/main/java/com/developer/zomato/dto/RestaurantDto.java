package com.developer.zomato.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private Long id;
    private String title;

    @Column(length = 1000)
    private List<String> images;

    private String description;

}
