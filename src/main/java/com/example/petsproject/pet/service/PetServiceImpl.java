package com.example.petsproject.pet.service;

import com.example.petsproject.pet.dao.PetRepository;
import com.example.petsproject.pet.domain.Pet;
import com.example.petsproject.pet.exception.EntityNotFoundException;
import com.example.petsproject.pet.model.CreatePetRequest;
import com.example.petsproject.pet.model.PetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository<Pet, Integer> petRepository;

    @Nonnull
    @Override
    public List<PetResponse> findAll() {
        return petRepository.findAll()
                .stream()
                .map(this::buildPetResponse)
                .collect(Collectors.toList());
    }

    @Nonnull
    @Override
    public PetResponse getById(@Nonnull Integer id) {
        return ofNullable(petRepository.findById(id))
                .map(this::buildPetResponse)
                .orElseThrow(() -> new EntityNotFoundException("Pet '" + id + "' not found"));
    }

    @Override
    public int create(@Nonnull CreatePetRequest request) {
        Pet pet = new Pet()
                .setName(request.getName())
                .setAge(request.getAge());
        pet = petRepository.save(pet);
        return pet.getId();
    }

    @Nonnull
    @Override
    public PetResponse update(@Nonnull Integer id, @Nonnull CreatePetRequest request) {
        Pet pet = ofNullable(petRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("Pet '" + id + "' not found"));

        ofNullable(request.getName()).ifPresent(pet::setName);
        ofNullable(request.getAge()).ifPresent(pet::setAge);

        pet = petRepository.save(pet);
        return buildPetResponse(pet);
    }

    @Override
    public void delete(@Nonnull Integer id) {
        petRepository.delete(id);
    }

    private PetResponse buildPetResponse(Pet pet) {
        return new PetResponse()
                .setId(pet.getId())
                .setName(pet.getName())
                .setAge(pet.getAge());
    }
}
