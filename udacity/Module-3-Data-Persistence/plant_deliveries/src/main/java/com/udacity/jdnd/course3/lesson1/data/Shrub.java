package com.udacity.jdnd.course3.lesson1.data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Shrub extends Plant{

    @Column(name="height_cm")
    private int heightCm; //any reasonable unit of measurement is fine

    @Column(name="width_cm")
    private int widthCm;

    public int getHeightCm() {
        return this.heightCm;
    }

    public void setHeightCm(int heightCm) {
        this.heightCm = heightCm;
    }

    public int getWidthCm() {
        return this.widthCm;
    }

    public void setWidthCm(int widthCm) {
        this.widthCm = widthCm;
    }


}
