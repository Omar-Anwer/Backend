package com.udacity.DogRestApi.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udacity.DogRestApi.service.DogService;

@RunWith(SpringRunner.class)
//Create a unit test for your API using the @WebMvcTest annotation
@WebMvcTest(DogController.class)
public class DogControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DogService dogService;

    @Test
    public void getAllDogs() throws Exception {
        mockMvc.perform(get("/dogs/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogs();
    }

    @Test
    public void getDogById() throws Exception {
        mockMvc.perform(get("/dogs/1"))
                .andExpect(status().isOk());

        verify(dogService, times(1)).retrieveDogById((long) 1);
    }



}
