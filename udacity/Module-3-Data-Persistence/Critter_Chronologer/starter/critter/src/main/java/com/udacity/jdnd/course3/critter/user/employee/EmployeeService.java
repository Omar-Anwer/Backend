package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee findById(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> 
            new EmployeeNotFoundException("Employee with ID " + id + " not found"));
        return employee;
    }


    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }


    public Employee save(Employee employee){
        return employeeRepository.save(employee);  
    }


    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        List<Employee> employees = employeeRepository
                .getAllByDaysAvailableContains(date.getDayOfWeek())
                .stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
        return employees;
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    public List<Employee> getEmployeesByIds(List<Long> employeeIds) {
		return employeeRepository.findAllById(employeeIds);
	}
}
