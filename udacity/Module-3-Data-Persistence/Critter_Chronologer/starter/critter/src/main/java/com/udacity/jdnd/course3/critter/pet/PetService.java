package com.udacity.jdnd.course3.critter.pet;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet save(Pet pet){
        return petRepository.save(pet);           
    }
    
    public Pet findById(Long id){
        Pet pet = petRepository.findById(id).orElseThrow(() -> 
            new PetNotFoundException("Pet with ID " + id + " not found"));

        return pet;
    }

    public List<Pet> findAllPets() {
       return petRepository.findAll();
    }

    public void delete(Long id) {
        if (!petRepository.existsById(id)) {
            throw new PetNotFoundException("Pet with ID " + id + " not found.");
        }
        petRepository.deleteById(id);
    }

    public List<Pet> getPetsByOwner(long ownerId) {
		return petRepository.findAllPetsByCustomerId(ownerId);
	}

    public List<Pet> getPetsByIds(List<Long> petIds) {
		return petRepository.findAllById(petIds);
	}
}
