package com.example.petsproject.pet.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;

@Data
@Accessors(chain = true)
public class CreatePetRequest {

    @NotBlank(message = "{field.not.empty}")
    private String name;

    @NotNull(message = "{field.not.empty}")
    @Min(value = 0, message = "{field.min.value}")
    @Max(value = 100, message = "{field.max.value}")
    private Integer age;
}
