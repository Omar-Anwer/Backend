package com.udacity.jdnd.course3.critter.pet;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Nationalized;

import com.udacity.jdnd.course3.critter.user.customer.Customer;

@Entity
//@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    @Nationalized
    private String name;

    @Column(name="birth_date")
    private LocalDate birthDate;

    //@Lob
    @Nationalized
    private String notes;

    // one pet belongs to one customer, if customer deleted his pets deleted
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Pet(){

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return this.type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getOwner() {
        return this.customer;
    }

    public void setOwner(Customer customer) {
        this.customer = customer;
    }
}
