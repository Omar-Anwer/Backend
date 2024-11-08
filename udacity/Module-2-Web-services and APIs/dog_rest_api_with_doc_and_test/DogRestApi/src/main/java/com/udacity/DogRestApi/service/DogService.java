package com.udacity.DogRestApi.service;

import java.util.List;

import com.udacity.DogRestApi.entity.Dog;

public interface DogService {
    List<Dog> retrieveDogs();


    Dog retrieveDogById(Long id);
     
    Dog saveDog(Dog dog);

    void deleteDog(Long id);


    List<String> retrieveDogName();
    List<String> retrieveDogBreed();
    List<String> retrieveDogOrigin();

    String retrieveDogNameById(Long id);
    String retrieveDogBreedById(Long id);
    String retrieveDogOriginById(Long id);
}
