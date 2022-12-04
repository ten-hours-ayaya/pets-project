package com.example.petsproject.pet.model;

import lombok.Data;

@Data
public class ErrorResponse {
    private final String type;
    private final String message;
}
