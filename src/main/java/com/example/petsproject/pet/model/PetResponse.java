package com.example.petsproject.pet.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PetResponse {
    private Integer id;
    private String name;
    private Integer age;
}
