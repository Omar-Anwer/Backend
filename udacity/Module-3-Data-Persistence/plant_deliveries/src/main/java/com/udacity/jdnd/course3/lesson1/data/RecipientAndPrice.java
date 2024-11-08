package com.udacity.jdnd.course3.lesson1.data;

import java.math.BigDecimal;

public class RecipientAndPrice {
    private String recipientName;
    private BigDecimal price;

 public RecipientAndPrice(String recipientName, BigDecimal price) {
       this.recipientName = recipientName;
       this.price = price;
   }

    public String getRecipientName() {
        return this.recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

 }