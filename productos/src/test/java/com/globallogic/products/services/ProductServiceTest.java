package com.globallogic.products.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.products.dtos.ProductDto;
import com.globallogic.products.entities.Product;
import com.globallogic.products.exceptions.NotEnoughStockException;
import com.globallogic.products.exceptions.ProductNotFoundException;
import com.globallogic.products.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Todos los productos de la lista deberian ser retornados")
    void getAllProductsTest() {
        var products = new ArrayList<Product>();
        products.add(
                Product.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(10).price(10).build());
        products.add(
                Product.builder().id("633b47c2ce972b55537e477d").description("desc B").stock(20).price(12).build());
        products.add(
                Product.builder().id("633b47c2ce972b55537e478d").description("desc C").stock(30).price(30).build());
        products.add(
                Product.builder().id("633b47c2ce972b55537e479d").description("desc D").stock(40).price(40).build());

        when(productRepository.findAll()).thenReturn(products);
        assertEquals(4, productService.getAllProducts().size());
    }

    @Test
    @DisplayName("deberia retornarse el DTO esperado")
    void getProductByIDTest() {
        when(productRepository.findById("633b47c2ce972b55537e476d")).thenReturn(Optional.of(
                Product.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(10).price(10).build()));
        var dto = productService.getProductById("633b47c2ce972b55537e476d");
        var expectedDto = ProductDto.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(10).price(10)
                .build();
        
        assertEquals(dto, expectedDto);
    }

    @Test
    @DisplayName("el stock deberia reducirse en 10 unidades")
    void updateProductStockTest() {
        var product = Product.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(20).price(10)
                .build();
        when(productRepository.findById("633b47c2ce972b55537e476d")).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        var dto = productService.updateProductStock("633b47c2ce972b55537e476d", 10);
        var expectedDto = ProductDto.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(10).price(10)
                .build();
        
        assertEquals(dto, expectedDto);
    }

    @Test
    @DisplayName("no deberia modificarse la cantidad de stock cuando el quantity solicitado es 0")
    void updateProductWithInsufficientStockTest() {
        var product = Product.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(20).price(10)
                .build();
        when(productRepository.findById("633b47c2ce972b55537e476d")).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        var dto = productService.updateProductStock("633b47c2ce972b55537e476d", 0);
        var expectedDto = ProductDto.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(20).price(10)
                .build();
        
        assertEquals(dto, expectedDto);
    }

    @Test
    @DisplayName("si no hay suficiente stock para el quantity solicitado, deberia lanzarse una excepcion")
    void notEnoughStockTest() {
        var product = Product.builder().id("633b47c2ce972b55537e476d").description("desc A").stock(20).price(10)
                .build();
        when(productRepository.findById("633b47c2ce972b55537e476d")).thenReturn(Optional.of(product));

        assertThrowsExactly(NotEnoughStockException.class,
                () -> productService.updateProductStock("633b47c2ce972b55537e476d", 1000));
    }

    @Test
    @DisplayName("si trato de actualizar el stock de un producto que no existe, lanzo excepcion")
    void updateNotFoundProductTest() {
        when(productRepository.findById("633b47c2ce972b55537e476d")).thenThrow(ProductNotFoundException.class);
        assertThrowsExactly(ProductNotFoundException.class,
                () -> productService.updateProductStock("633b47c2ce972b55537e476d", 1000));
    }

    @Test
    @DisplayName("si no logro encontrar el producto buscado, lanzo una excepcion")
    void productNotFoundTest() {
        when(productRepository.findById("633b47c2ce972b55537e476d")).thenThrow(ProductNotFoundException.class);
        assertThrowsExactly(ProductNotFoundException.class,
                () -> productService.getProductById("633b47c2ce972b55537e476d"));
    }

}
