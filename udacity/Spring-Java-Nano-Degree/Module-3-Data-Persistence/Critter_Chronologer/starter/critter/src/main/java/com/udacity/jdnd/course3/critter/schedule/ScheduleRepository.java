package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.Employee;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{

    // returns all schedules by pet
	List<Schedule> findByPets(Pet pet);

	// returns all schedules by employee
	List<Schedule> findByEmployees(Employee employee);

	//List<Schedule> findByPets(List<Pet> pets);

	//List<Schedule> findByEmployees(List<Employee> employees);
}
