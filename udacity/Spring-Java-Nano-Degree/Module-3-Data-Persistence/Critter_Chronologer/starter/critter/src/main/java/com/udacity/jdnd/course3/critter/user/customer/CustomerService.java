package com.udacity.jdnd.course3.critter.user.customer;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.PetRepository;

@Service
@Transactional
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer findById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(() -> 
            new CustomerNotFoundException("Customer with ID " + id + " not found"));
        return customer;
    }


    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }


    public Customer save(Customer customer){
        return customerRepository.save(customer);  
    }


    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        customerRepository.deleteById(id);
    }

    public List<Customer> findAllCustomers() {
       return customerRepository.findAll();
    }

    public Customer getCustomerByPetId(long id) {
		return customerRepository.findByPets(petRepository.getOne(id));
	}

      
}
