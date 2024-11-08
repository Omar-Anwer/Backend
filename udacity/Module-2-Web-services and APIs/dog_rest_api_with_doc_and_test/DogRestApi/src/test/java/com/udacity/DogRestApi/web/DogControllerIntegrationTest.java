package com.udacity.DogRestApi.web;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.DogRestApi.entity.Dog;


@RunWith(SpringRunner.class)
// Create an integration test for your API using the @SpringBootTest annotation, assign a random port to avoid conflict
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DogControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllDogs() {
        @SuppressWarnings("rawtypes")
        ResponseEntity<List> response =
                  this.restTemplate.getForEntity("http://localhost:" + port + "/dogs/", List.class);

          assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void getDogById() {
        ResponseEntity<Dog> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/dogs/1", Dog.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
