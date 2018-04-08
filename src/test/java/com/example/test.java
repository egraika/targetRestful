package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import com.example.controller.ProductInformationController;
import com.example.dao.PricingRepo;
import com.example.entity.CurrentPricing;
import com.example.entity.Pricing;

import junit.framework.Assert;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;

import org.json.JSONException;
import org.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
public class test {
	
	 private MockMvc mockMvc;
	 
	 @Mock
	 private PricingRepo PricingRepo;

	 @InjectMocks
	 private ProductInformationController ProductInformationController;
	 
	 @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders
	                .standaloneSetup(ProductInformationController)
	                .build();
	    }
	    
    @Test
    public void testGet() throws Exception {
    	Pricing pricing = new Pricing();
    	CurrentPricing current_price = new CurrentPricing();
    	current_price.setCurrency_Code("USD");
    	current_price.setValue(13.49);
    	pricing.setCurrent_Price(current_price);
    	pricing.setProductID(13960428);
    	
    	when(PricingRepo.findByProductID(13960428)).thenReturn(pricing);
    	
    	MvcResult mvcResult = mockMvc.perform(get("/products/{id}", 13960428).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    	
        verify(PricingRepo, times(1)).findByProductID(13960428);
        verifyNoMoreInteractions(PricingRepo);
        
        System.out.println(mvcResult.getResponse().getContentAsString());
        String result = mvcResult.getResponse().getContentAsString();
        Assert.assertEquals("{\"productID\":13960428,\"name\":\"The Big Lebowski (Blu-ray)\",\"current_Price\":{\"value\":13.49,\"currency_Code\":\"USD\"}}", result);

    }
}