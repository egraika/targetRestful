package com.example.controller;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dao.PricingRepo;
import com.example.entity.Pricing;
import com.example.entity.CurrentPricing;

@RestController
public class ProductInformationController {

	@Autowired
	private PricingRepo PricingRepo;

    @RequestMapping(value="/products/{id}",method = RequestMethod.GET)
    public Pricing getProductInfo(@PathVariable("id") long id) throws JSONException {
    	Pricing pricing = PricingRepo.findByProductID(id);
    	String title = retrieveName();
    	pricing.setName(title);
        return pricing;
    }
    
    public String retrieveName() throws JSONException {
    	final String uri = "https://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        
        JSONObject jsonObj = new JSONObject(result);
        JSONObject productJSON = jsonObj.getJSONObject("product");
        JSONObject itemJSON = productJSON.getJSONObject("item");
        JSONObject productDescription = itemJSON.getJSONObject("product_description");
        String title = productDescription.getString("title");
        
		return title;
    }
    
    @RequestMapping(value="/products/{id}",method = RequestMethod.PUT,consumes = "application/json")
    public Pricing setProductValue(@PathVariable("id") long id, @RequestBody double value) throws JSONException {
    	Pricing pricing = PricingRepo.findByProductID(id);
    	CurrentPricing current_price = pricing.getCurrent_Price();
    	current_price.setValue(value);
    	pricing.setCurrent_Price(current_price);
    	PricingRepo.save(pricing);
        return pricing;
    }
    
}