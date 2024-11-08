package com.udacity.jdnd.course3.lesson1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.lesson1.data.Delivery;
import com.udacity.jdnd.course3.lesson1.data.RecipientAndPrice;
import com.udacity.jdnd.course3.lesson1.repository.DeliveryRepository;

@Service
public class DeliveryService {
   @Autowired
   DeliveryRepository deliveryRepository;

   public Long save(Delivery delivery) {
       delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
       deliveryRepository.persist(delivery);
       return delivery.getId();
   }

      public RecipientAndPrice getBill(Long deliveryId){
       return deliveryRepository.getBill(deliveryId);
   }
}