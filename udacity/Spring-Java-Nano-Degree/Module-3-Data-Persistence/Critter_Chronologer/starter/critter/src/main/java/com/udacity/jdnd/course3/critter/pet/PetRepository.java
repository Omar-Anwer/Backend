package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional    
/*  meaning that any database operations performed within the marked method or class will be executed within a transaction. 
    If the transaction is successful, the changes will be committed to the database
*/
public interface PetRepository extends JpaRepository<Pet, Long>{

    // returns all the pets of specific owner
	List<Pet> findAllPetsByCustomerId(Long id);
}
