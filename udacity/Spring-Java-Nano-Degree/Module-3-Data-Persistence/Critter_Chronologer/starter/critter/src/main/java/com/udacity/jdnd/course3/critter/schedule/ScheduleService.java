package com.udacity.jdnd.course3.critter.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.customer.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeRepository;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PetRepository petRepository;

    
    public Schedule save(Schedule schedule) {

		return scheduleRepository.save(schedule);
	}

    public List<Schedule> getAllSchedules() {
		return scheduleRepository.findAll();
	}

	public List<Schedule> getAllSchedulesForEmployee(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
		return scheduleRepository.findByEmployees(employee);
	}

	public List<Schedule> getAllSchedulesForPet(long petId) {
        Pet pet = petRepository.getOne(petId);
		return scheduleRepository.findByPets(pet);
	}

    public List<Schedule> getAllSchedulesForCustomer(long id) {

        List<Schedule> schedulesCustomer = new ArrayList<>();

        List<Pet> customerPets = customerRepository.getOne(id).getPets();

        for (Pet pet : customerPets) {
            schedulesCustomer.addAll(scheduleRepository.findByPets(pet));
        }
		return schedulesCustomer;
	}
}
