package com.udacity.pricing;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.domain.price.PriceRepository;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	@Autowired
	private PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void findAll() throws Exception {
		// List<Price> prices = (List<Price>) priceRepository.findAll();
		Optional<List<Price>> optionalPrices = Optional.ofNullable((List<Price>) priceRepository.findAll());
		assertTrue(optionalPrices.isPresent());
		List<Price> priceList = optionalPrices.get();
		mockMvc.perform(get("/prices/"))
				.andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").exists())
				.andExpect(jsonPath("$._embedded.prices").exists())
				.andExpect(jsonPath("$._embedded.prices[0].currency").value(priceList.get(0).getCurrency()))
				.andExpect(jsonPath("$._embedded.prices[0].price").value(priceList.get(0).getPrice().doubleValue()));
				// .andExpect(jsonPath("$_embedded.prices[0].currency").value("USD"))
				// .andExpect(jsonPath("$_embedded.prices[0].price").isNumber()); 
	}

	@Test
	public void findById() throws Exception {
		Long vehicleId = 1L;
		Optional<Price> optionalPrice = priceRepository.findById(vehicleId);
		assertTrue(optionalPrice.isPresent());
		Price price = optionalPrice.get();
		mockMvc.perform(get("/prices/{id}", vehicleId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.currency").exists())
				.andExpect(jsonPath("$.price").exists())
				.andExpect(jsonPath("$.currency").value(price.getCurrency()))
                .andExpect(jsonPath("$.price").value(price.getPrice().doubleValue())); 
			 	// .andExpect(jsonPath("$.currency").value("USD"))
				// .andExpect(jsonPath("$.price").isNumber()); 
	}

}
