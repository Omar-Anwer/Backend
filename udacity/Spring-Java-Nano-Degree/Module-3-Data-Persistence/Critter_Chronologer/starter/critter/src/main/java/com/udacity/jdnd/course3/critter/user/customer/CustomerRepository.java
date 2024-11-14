package com.udacity.jdnd.course3.critter.user.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.pet.Pet;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Customer findByPets(Pet pet);
}
