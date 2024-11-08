package com.udacity.jdnd.course3.critter.pet;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.critter.user.customer.Customer;
import com.udacity.jdnd.course3.critter.user.customer.CustomerService;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

   @Autowired
   private PetService petService;

   @Autowired
   private CustomerService customerService;

   @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        Pet savedPet = petService.save(pet);
        PetDTO savedPetDTO = convertPetToPetDTO(savedPet);

        Customer petOwner = customerService.findById(petDTO.getOwnerId());
        petOwner.addPet(savedPet);

		return savedPetDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        PetDTO savedPetDTO = convertPetToPetDTO(petService.findById(petId));
        return savedPetDTO;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();
		List<PetDTO> petDTOs = pets.stream()
                                   .map(pet -> convertPetToPetDTO(pet))
                                   .collect(Collectors.toList());
		return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.getPetsByOwner(ownerId);
		List<PetDTO> petDTOs = pets.stream()
                                   .map(pet -> convertPetToPetDTO(pet))
                                   .collect(Collectors.toList());
		return petDTOs;
    }
 
    public PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO, "customer", "petType");

        //Adds the customer to the PetDTO instance
        Customer owner = customerService.findById(pet.getOwner().getId());
        petDTO.setOwnerId(owner.getId());
        petDTO.setType(pet.getType());

        return petDTO;
    }

    public Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet, "ownerId", "type");

        Customer owner = customerService.findById(petDTO.getOwnerId());
        pet.setOwner(owner);
        pet.setType(petDTO.getType());

        return pet;
    }
}
