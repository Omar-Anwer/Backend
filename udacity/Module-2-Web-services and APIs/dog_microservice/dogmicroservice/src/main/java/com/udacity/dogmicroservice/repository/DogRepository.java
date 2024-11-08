package com.udacity.dogmicroservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.udacity.dogmicroservice.entity.Dog;

public interface DogRepository extends CrudRepository<Dog, Long>{

}
