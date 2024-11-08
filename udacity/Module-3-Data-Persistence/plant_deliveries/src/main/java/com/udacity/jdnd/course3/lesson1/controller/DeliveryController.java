package com.udacity.jdnd.course3.lesson1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jdnd.course3.lesson1.data.Delivery;
import com.udacity.jdnd.course3.lesson1.data.RecipientAndPrice;
import com.udacity.jdnd.course3.lesson1.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
   @Autowired
   DeliveryService deliveryService;

   @PostMapping
   public Long scheduleDelivery(@RequestBody Delivery delivery) {
       return deliveryService.save(delivery);
   }

    @GetMapping("/bill/{deliveryId}")
   public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
       return deliveryService.getBill(deliveryId);
   }
}