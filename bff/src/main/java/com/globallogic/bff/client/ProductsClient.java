package com.globallogic.bff.client;

import java.util.List;

import com.globallogic.bff.dto.StockMovementDto;
import org.springframework.cloud.openfeign.FeignClient;
import com.globallogic.bff.dto.ProductDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "PRODUCTS", path = "${javaneta-services.base-path}")
public interface ProductsClient {

    @GetMapping("/products")
    List<ProductDTO> getAllProducts();

    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable String id);


    @PatchMapping("products/{productID}/")
    ProductDTO updateProductStock(@PathVariable String productID, @RequestBody StockMovementDto stockMovementDto);
}
