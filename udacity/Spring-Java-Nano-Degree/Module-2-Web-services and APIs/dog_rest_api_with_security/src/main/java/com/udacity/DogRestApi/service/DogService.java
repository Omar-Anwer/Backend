package com.udacity.DogRestApi.service;

import java.util.List;

import com.udacity.DogRestApi.entity.Dog;

public interface DogService {
    List<Dog> retrieveDogs();

    
    List<String> retrieveDogName();
    List<String> retrieveDogBreed();
    List<String> retrieveDogOrigin();

    Dog retrieveDogById(Long id);

    String retrieveDogNameById(Long id);
    String retrieveDogBreedById(Long id);
    String retrieveDogOriginById(Long id);
}
