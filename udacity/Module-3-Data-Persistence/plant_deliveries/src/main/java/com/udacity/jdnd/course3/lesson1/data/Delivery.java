package com.udacity.jdnd.course3.lesson1.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;


//data package might contain a ‘delivery’ package and an ‘inventory’ package

// validate queries before launching the app
@NamedQueries({
@NamedQuery(
    name = "Delivery.findByName", 
    query = "select d from Delivery d where d.name = :name")
   })
@Entity
@NotNull
//@Table(name="DELIVERIES")
public class Delivery {
    @Id
    @GeneratedValue  //AUTO
    private Long id;

    // shoud remove this and add recipientId
    @Nationalized
    private String name;

    @Nationalized
    @Column(name = "address_full", length = 512, nullable=false)
    private String address;
    

    // @CreatedDate
    //private LocalDateTime createdAt;
    // @LastModifiedDate
   // private LocalDateTime modifiedAt;
    private LocalDateTime deliveryTime;

    @Type(type = "yes_no")
    private Boolean completed = false;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "status", nullable = false)
    // private String status;  // e.g., "Pending", "Delivered", "Canceled"

   /*
    * Don't load list of plants immediately when retrieving a Delivery Entity (fetch=FetchType.LAZY)
    * save/remove any associated list of plants  when Delivery is saved/removed (cascade = CascadeType.ALL)
    */
   @OneToMany(mappedBy="delivery", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
   private List<Plant> plants;


//    @Lob
//    @Basic(fetch = FetchType.LAZY)
//    @Column(name = "notes")
//     private String notes;

//    @Column(name = "tracking_number", unique = true, length = 100)
//     private String trackingNumber;


//    @Size(max = 100)
//    @Column(name = "tracking_number", unique = true)
//     private String trackingNumber;

    public Delivery(){
            
    }

    public Delivery(String name, String address, LocalDateTime deliveryTime){
        this.name = name;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }

    public Delivery(Long id, String name, String address, LocalDateTime deliveryTime){
        this.id = id;
        this.name = name;
        this.address = address;
        this.deliveryTime = deliveryTime;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public List<Plant> getPlants() {
        return this.plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
