package com.udacity.jdnd.course3.critter.user.employee;

import java.time.DayOfWeek;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Nationalized;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nationalized
    private String name;

    /*
        Variables that create collection (association) tables between:
        -Employee and EmployeeSkill
        -Employee and DayOfWeek
     */
    @ElementCollection(targetClass = EmployeeSkill.class)
    //@Column
    //@JoinTable(name="employee_skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    //@JoinTable(name="employee_days_Available")
    //@Column
    private Set<DayOfWeek> daysAvailable;

    // @ManyToMany(mappedBy = "employees")
	// private List<Schedule> schedules;

    public Employee(){
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeSkill> getSkills() {
        return this.skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return this.daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }


}
