package com.globallogic.bff.service;

import java.util.List;

import com.globallogic.bff.dto.SalesDTO;
import com.globallogic.bff.dto.SoldProductDTO;
import com.globallogic.bff.dto.StockMovementDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.globallogic.bff.client.ProductsClient;
import com.globallogic.bff.dto.ProductDTO;

@Service
public class ProductService {

    private final ProductsClient client;

    public ProductService(ProductsClient client) {
        this.client = client;
    }

    public List<ProductDTO> getAllProducts() {
        return client.getAllProducts();
    }

    public ProductDTO getProductById(String id) {
        return client.getProductById(id);
    }

    public ProductDTO updateProductStock(String productId, StockMovementDto stockMovement) {
        return client.updateProductStock(productId, stockMovement);
    }

    public void updateProductsStock(SalesDTO sale) {
        List<SoldProductDTO> saleProducts = sale.getProducts();
        for (SoldProductDTO product : saleProducts) {
            var stockMovementDto = StockMovementDto.builder().productQuantity(product.getQuantity()).build();
            try {
                updateProductStock(product.getProductId(), stockMovementDto);
            } catch (Exception exception) {
                rollbackProductsStock(saleProducts, product);
                throw exception;
            }

        }
    }

    @Async
    private void rollbackProductsStock(List<SoldProductDTO> products, SoldProductDTO failedProduct) {
        var cutPoint = products.indexOf(failedProduct);
        products = products.subList(0, cutPoint);
        for (SoldProductDTO product : products) {
            var stockMovementDto = StockMovementDto.builder().productQuantity(-product.getQuantity()).build();
            updateProductStock(product.getProductId(), stockMovementDto);
        }

    }

}
