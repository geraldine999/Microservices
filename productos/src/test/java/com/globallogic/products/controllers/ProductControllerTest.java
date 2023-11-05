package com.globallogic.products.controllers;

import com.globallogic.products.dtos.ProductDto;
import com.globallogic.products.dtos.StockMovementDto;
import com.globallogic.products.services.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    
    @InjectMocks
    private ProductController controller;

    @Mock
    private ProductServiceImpl productService;
    

    @Test 
    @DisplayName("Should return Ok status (200) if request is well built")
    void shouldReturnOkStatusIfRequestIsWellBuilt() throws Exception {
        
        var request = StockMovementDto.builder()
                .productQuantity(2)
                .build();
                
        String content = (new ObjectMapper()).writeValueAsString(request);
                
        Mockito.when(this.productService.updateProductStock("1", 2)).thenReturn(null);
                 
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.controller).build();


        ResultActions actualPerformResult = buildResult.perform(requestBuilder);


        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());
    
    }

    @Test
    @DisplayName("Should return Bad Request (400) if request is wrongly built")
    void shouldReturnBadRequestIfRequestIsWronglyBuilt() throws Exception {

        var request = StockMovementDto.builder()
                .productQuantity(-2)
                .build();

        String content = (new ObjectMapper()).writeValueAsString(request);

        verify(productService, never()).updateProductStock(any(), any());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.controller).build();


        ResultActions actualPerformResult = buildResult.perform(requestBuilder);


        actualPerformResult.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @DisplayName("Should return 200 status when returning all products")
    void shouldReturn200StatusWhenReturningAllProducts() throws Exception {

        Mockito.when(productService.getAllProducts()).thenReturn(null);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.controller).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Should return products")
    void shouldReturnProducts() {

        double price = 23.2;

        var response = ProductDto.builder()
                .price(price)
                .build();

        Mockito.when(productService.getAllProducts()).thenReturn(List.of(response));

        assertEquals(price, controller.getAllProducts().get(0).getPrice());


    }

    @Test
    @DisplayName("Should return product")
    void shouldReturnProduct() {

        String id = "1";

        var response = ProductDto.builder()
                .id(id)
                .build();

        Mockito.when(productService.getProductById(id)).thenReturn(response);

        assertEquals(id, controller.getProductById(id).getId());


    }

    @Test
    @DisplayName("Should return 200 status when returning product by id ")
    void shouldReturn200StatusWhenReturningProductById() throws Exception {

        Mockito.when(productService.getProductById(any())).thenReturn(any());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/1");

        MockMvc buildResult = MockMvcBuilders.standaloneSetup(this.controller).build();

        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        actualPerformResult.andExpect(MockMvcResultMatchers.status().isOk());

    }

}
