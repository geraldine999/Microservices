package com.globallogic.products.services;

import java.util.List;

import com.globallogic.products.dtos.ProductDto;

public interface ProductService {
    public List<ProductDto> getAllProducts();

    public ProductDto getProductById(String id);

    public ProductDto updateProductStock(String productID, Integer quantity);
}
