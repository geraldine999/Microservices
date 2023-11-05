package com.globallogic.products.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.globallogic.products.dtos.ProductDto;
import com.globallogic.products.entities.Product;
import com.globallogic.products.exceptions.NotEnoughStockException;
import com.globallogic.products.exceptions.ProductNotFoundException;
import com.globallogic.products.repositories.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    
    @Override
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepository.findAll();

        List<ProductDto> dtos = new ArrayList<>();

        for (Product p : products) {
            var dto = new ProductDto();
            BeanUtils.copyProperties(p, dto);
            dtos.add(dto);
        }

        return dtos;
    }
    
    @Override
    public ProductDto getProductById(String id) {
        var product = this.findProductByID(id);
        return ProductDto.builder().id(product.getId()).description(product.getDescription()).price(product.getPrice())
                .stock(product.getStock()).build();
    }
    
    @Override
    public ProductDto updateProductStock(String productID, Integer quantity) {
        var product = this.findProductByID(productID);
        var remainingStock = product.getStock() - quantity;
        if (remainingStock < 0) {
            throw new NotEnoughStockException(productID);
        }

        product.setStock(remainingStock);
        productRepository.save(product);

        return ProductDto.builder().id(product.getId()).description(product.getDescription()).price(product.getPrice())
                .stock(product.getStock()).build();
    }

    private Product findProductByID(String productID) {
        return productRepository.findById(productID)
                .orElseThrow(() -> new ProductNotFoundException("No se encontró un producto con ese id."));
    }

}
