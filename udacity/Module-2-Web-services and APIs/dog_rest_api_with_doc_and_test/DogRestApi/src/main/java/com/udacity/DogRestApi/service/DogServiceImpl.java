package com.udacity.DogRestApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.DogRestApi.entity.Dog;
import com.udacity.DogRestApi.exception.DogNotFoundException;
import com.udacity.DogRestApi.repository.DogRepository;

@Service
public class DogServiceImpl implements DogService {
    @Autowired
    DogRepository dogRepository;

    @Override
    public List<Dog> retrieveDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    public List<String> retrieveDogName() {
        return (List<String>) dogRepository.findAllName();
    }

    @Override
    public List<String> retrieveDogBreed() {
        return (List<String>) dogRepository.findAllBreed();
    }

    @Override
    public List<String> retrieveDogOrigin() {
        return (List<String>) dogRepository.findAllOrigin();
    }

    @Override
    public String retrieveDogBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }

    @Override
    public String retrieveDogNameById(Long id){
        Optional<String> optionalName = Optional.ofNullable(dogRepository.findNameById(id));
        String name = optionalName.orElseThrow(DogNotFoundException::new);
        return name;
    }

    @Override
    public String retrieveDogOriginById(Long id){
        Optional<String> optionalOrigin = Optional.ofNullable(dogRepository.findOriginById(id));
        String origin = optionalOrigin.orElseThrow(DogNotFoundException::new);
        return origin;
    }

    @Override
    public Dog retrieveDogById(Long id) {
        Optional<Dog> optionalDog = Optional.ofNullable(dogRepository.findDogById(id));
        Dog dog = optionalDog.orElseThrow(DogNotFoundException::new);
        return dog;
    }

    @Override
    public Dog saveDog(Dog dog) {
        if (dogRepository.existsById(dog.getId())) {
            throw new IllegalArgumentException("Dog with this ID already exists.");
        }
        return dogRepository.save(dog); // Works for both create and update
    }

    @Override
    public void deleteDog(Long id) {
        if (dogRepository.existsById(id) == false) {
            throw new IllegalArgumentException("Dog with this ID does not exist.");
        }
        dogRepository.deleteById(id);
    }
}
