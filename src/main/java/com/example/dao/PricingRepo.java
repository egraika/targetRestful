package com.example.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.entity.Pricing;

@RepositoryRestResource(collectionResourceRel = "pricing", path = "pricing")
public interface PricingRepo extends MongoRepository<Pricing, String> {

	//List<Pricing> findByLastName(@Param("name") String name);

}