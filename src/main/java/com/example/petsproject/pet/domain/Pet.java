package com.example.petsproject.pet.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Pet {
    private Integer id;
    private String name;
    private Integer age;
}
