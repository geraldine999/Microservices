package com.globallogic.products.controllers;

import java.util.List;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.products.dtos.ProductDto;
import com.globallogic.products.dtos.StockMovementDto;
import com.globallogic.products.services.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    
    @PatchMapping(path = "/{productID}")
    public ProductDto updateProductStock(@PathVariable String productID, @Valid @RequestBody StockMovementDto stockMovementDto) {
        return productService.updateProductStock(productID, stockMovementDto.getProductQuantity());
    }
}
