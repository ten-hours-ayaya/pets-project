package com.example.petsproject.pet.dao;

import com.example.petsproject.pet.domain.Pet;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PetRepositoryImpl implements PetRepository<Pet, Integer> {

    private final AtomicInteger counter = new AtomicInteger(1);
    private final Map<Integer, Pet> pets = new ConcurrentHashMap<>();

    @Nonnull
    @Override
    public List<Pet> findAll() {
        return new ArrayList<>(pets.values());
    }

    @Nullable
    @Override
    public Pet findById(@Nonnull Integer id) {
        return pets.get(id);
    }

    @Override
    public Pet save(@Nonnull Pet pet) {
        Integer id = pet.getId();
        if(id == null) {
            id = counter.getAndIncrement();
            pet.setId(id);
        }
        pets.put(id, pet);
        return pet;
    }

    @Override
    public void delete(Integer id) {
        pets.remove(id);
    }
}
