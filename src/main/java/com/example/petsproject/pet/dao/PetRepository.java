package com.example.petsproject.pet.dao;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface PetRepository<ENTITY, ID> {

    @Nonnull
    List<ENTITY> findAll();

    @Nullable
    ENTITY findById(@Nonnull ID id);

    ENTITY save(@Nonnull ENTITY entity);

    void delete(ID id);
}
