package com.developer.zomato.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchRequest {

    private String fullName;
    private String email;
    private String status;
}
