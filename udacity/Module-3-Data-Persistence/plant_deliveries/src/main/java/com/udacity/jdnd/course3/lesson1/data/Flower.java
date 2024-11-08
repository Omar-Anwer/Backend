package com.udacity.jdnd.course3.lesson1.data;

import javax.persistence.Entity;

@Entity
public class Flower extends Plant{
    private String color;

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
