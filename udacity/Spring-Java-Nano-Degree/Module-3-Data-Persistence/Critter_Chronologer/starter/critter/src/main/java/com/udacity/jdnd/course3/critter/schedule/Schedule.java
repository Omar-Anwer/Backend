package com.udacity.jdnd.course3.critter.schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.employee.Employee;
import com.udacity.jdnd.course3.critter.user.employee.EmployeeSkill;

@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToMany(targetEntity = Employee.class)
    private List<Employee> employees;

    @ManyToMany(targetEntity = Pet.class)
    private List<Pet> pets;

    private LocalDate date;

    @ElementCollection
    private Set<EmployeeSkill> activities;

    public Schedule(){

    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<EmployeeSkill> getActivities() {
        return this.activities;
    }

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

}
