package com.example.petsproject.pet.service;

import com.example.petsproject.pet.model.CreatePetRequest;
import com.example.petsproject.pet.model.PetResponse;

import javax.annotation.Nonnull;
import java.util.List;

public interface PetService {
    @Nonnull
    List<PetResponse> findAll();

    @Nonnull
    PetResponse getById(@Nonnull Integer id);

    int create(@Nonnull CreatePetRequest request);

    @Nonnull
    PetResponse update(@Nonnull Integer id, @Nonnull CreatePetRequest request);

    void delete(@Nonnull Integer id);
}
