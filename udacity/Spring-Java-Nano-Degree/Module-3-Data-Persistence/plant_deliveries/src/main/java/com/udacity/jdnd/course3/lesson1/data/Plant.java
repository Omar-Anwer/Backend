package com.udacity.jdnd.course3.lesson1.data;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.lesson1.controller.PlantView;

@Entity
//@Table(name="PLANTS")
@Inheritance(strategy = InheritanceType.JOINED) // Uses InheritanceType.JOINED to store shared fields in 'plant' and unique fields in subclass tables
public class Plant {
    @Id
    @GeneratedValue       //(strategy=GenerationType.AUTO)
    private Long id; 

    @JsonView(PlantView.Public.class)
    @Nationalized      //// should use @Nationalized instead of @Type=nstring
    private String name;

    @JsonView(PlantView.Public.class)
    @Column(precision=12, scale=4) // 12 precision and 4 decimal places 
    private BigDecimal price;     // BigDecimal is the standard Java class for currency math

    @ManyToOne(fetch = FetchType.LAZY) //many plants can belong to one delivery, don't retrieve delivery if we don't need it
    @JoinColumn(name = "delivery_id")  //map the join column in the plant table
    private Delivery delivery;

    public Plant(){

    }
    
    public Plant(String name, double price){
        this.name = name;
        this.price = BigDecimal.valueOf(price);
    }

    public Plant(Long id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

}
