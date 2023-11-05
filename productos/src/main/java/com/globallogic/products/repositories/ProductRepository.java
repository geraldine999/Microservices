package com.globallogic.products.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.globallogic.products.entities.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}
