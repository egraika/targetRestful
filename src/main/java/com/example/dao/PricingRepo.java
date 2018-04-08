package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entity.Pricing;
import com.example.entity.CurrentPricing;

@RepositoryRestResource(collectionResourceRel = "pricing", path = "pricing")
public interface PricingRepo extends MongoRepository<Pricing, String> {

	Pricing findByProductID(@Param("ProductID") long ProductID);
	
}