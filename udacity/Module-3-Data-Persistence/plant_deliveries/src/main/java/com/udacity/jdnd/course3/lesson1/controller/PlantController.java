package com.udacity.jdnd.course3.lesson1.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.jdnd.course3.lesson1.data.Plant;
import com.udacity.jdnd.course3.lesson1.dto.PlantDTO;
import com.udacity.jdnd.course3.lesson1.service.PlantService;

@RestController
@RequestMapping("/plant")
public class PlantController {

    @Autowired
    private PlantService plantService;


    @GetMapping()
    public PlantDTO getPlantDTO(String name){
        return convertPlantToPlantDTO(plantService.getPlantByName(name));
    }

    @JsonView(PlantView.Public.class)
    public Plant getFilteredPlant(String name){
        return plantService.getPlantByName(name);
    }

    private PlantDTO convertPlantToPlantDTO(Plant plant){
        PlantDTO plantDTO = new PlantDTO();
        BeanUtils.copyProperties(plant, plantDTO);
        return plantDTO;
    }
    ////////////////////////////
    @GetMapping("/delivered/{id}")
   public Boolean delivered(@PathVariable Long id) {
       return plantService.delivered(id);
   }

   @GetMapping("/under-price/{price}")
   @JsonView(PlantView.Public.class)
   public List<Plant> plantsCheaperThan(@PathVariable BigDecimal price) {
       return plantService.findPlantsBelowPrice(price);
   }
}
