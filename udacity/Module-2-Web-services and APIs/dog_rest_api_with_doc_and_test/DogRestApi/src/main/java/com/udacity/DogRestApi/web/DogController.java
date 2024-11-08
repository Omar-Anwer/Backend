package com.udacity.DogRestApi.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.DogRestApi.entity.Dog;
import com.udacity.DogRestApi.service.DogService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
// customize error messages
@ApiResponses(value = {
    @ApiResponse(code=400, message = "This a bad request, please follow the API documentation for the proper request format."),
    @ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized."),
    @ApiResponse(code=500, message = "The server is down. Please make sure that the dog microservice/dog server is running.")
})
@RequestMapping("/dogs")
public class DogController {
    private DogService dogService;

    @Autowired
    public void setDogService(DogService dogService) {
        this.dogService = dogService;
    }

    // Create a Dog
    @PostMapping("/save")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        Dog savedDog = dogService.saveDog(dog);
        return new ResponseEntity<>(savedDog, HttpStatus.CREATED);
    }

    // Update Dog
    // @PutMapping("/update")
    // public ResponseEntity<Dog> updateDog(@RequestBody Dog dog) {
    // }

    // Delete Dog by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable final Long id) {
        dogService.deleteDog(id);
        return ResponseEntity.noContent().build();
    }

    // Get Dog by ID
    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDog(@PathVariable final Long id) {
        Dog dog = dogService.retrieveDogById(id);
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    // Get All Dogs
    @GetMapping()
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> list = dogService.retrieveDogs();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<List<String>> getAllNames() {
        List<String> list = dogService.retrieveDogName();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/breed")
    public ResponseEntity<List<String>> getAllBreeds() {
        List<String> list = dogService.retrieveDogBreed();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/origin")
    public ResponseEntity<List<String>> getAllOrigins() {
        List<String> list = dogService.retrieveDogOrigin();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}/name")
    public ResponseEntity<String> getName(@PathVariable final Long id) {
        String name = dogService.retrieveDogNameById(id);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreed(@PathVariable final Long id) {
        String breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }

    @GetMapping("/{id}/origin")
    public ResponseEntity<String> getOrigin(@PathVariable final Long id) {
        String origin = dogService.retrieveDogOriginById(id);
        return new ResponseEntity<>(origin, HttpStatus.OK);
    }
}
