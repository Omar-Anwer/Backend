package com.udacity.DogRestApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udacity.DogRestApi.entity.Dog;

public interface DogRepository extends CrudRepository<Dog, Long> {

    @Query ("select d from Dog d where d.id=:id")
    Dog findDogById(Long id);

    @Query("select d.id, d.name from Dog d where d.id=:id")
    String findNameById(Long id);

    @Query("select d.id, d.breed from Dog d where d.id=:id")
    String findBreedById(Long id);

    @Query("select d.id, d.origin from Dog d where d.id=:id")
    String findOriginById(Long id);

    @Query("select d.id, d.breed from Dog d")
    List<String> findAllBreed();

    @Query("select d.id, d.name from Dog d")
    List<String> findAllName();

    @Query("select d.id, d.origin from Dog d")
    List<String> findAllOrigin();
}
