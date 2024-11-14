package com.udacity.DogRestApi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.DogRestApi.entity.Dog;
import com.udacity.DogRestApi.service.DogService;

@RestController
@RequestMapping("/dogs")
public class DogController {
    private DogService dogService;

    @Autowired
    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> list = dogService.retrieveDogs();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getDogNames() {
        List<String> list = dogService.retrieveDogName();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/breed")
    public ResponseEntity<List<String>> getDogBreeds() {
        List<String> list = dogService.retrieveDogBreed();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/origin")
    public ResponseEntity<List<String>> getDogOrigins() {
        List<String> list = dogService.retrieveDogOrigin();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable final Long id) {
        Dog dog = dogService.retrieveDogById(id);
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getNameByID(@PathVariable final Long id) {
        String name = dogService.retrieveDogNameById(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreedByID(@PathVariable final Long id) {
        String breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }

    @GetMapping("/{id}/origin")
    public ResponseEntity<String> getOriginByID(@PathVariable final Long id) {
        String origin = dogService.retrieveDogOriginById(id);
        return new ResponseEntity<>(origin, HttpStatus.OK);
    }
}
