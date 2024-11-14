package com.udacity.jdnd.course3.lesson1.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jdnd.course3.lesson1.data.Plant;
import com.udacity.jdnd.course3.lesson1.repository.PlantRepository;

@Service
public class PlantService {

    @Autowired
    PlantRepository plantRepository;

    public Plant getPlantById(Long id){
        return new Plant();
    }

    public Plant getPlantByName(String name){
        return new Plant();
    }
////////////////////////////////////////////////////

    public Long save(Plant plant){
        return plantRepository.save(plant).getId();
    }

    public Boolean delivered(Long id){
       // return plantRepository.deliveryCompleted(id); 
       return plantRepository.existsPlantByIdAndDeliveryCompleted(id, true);
   }

    public List<Plant> findPlantsBelowPrice(BigDecimal price) {
        return plantRepository.findByPriceLessThan(price);
}

}
