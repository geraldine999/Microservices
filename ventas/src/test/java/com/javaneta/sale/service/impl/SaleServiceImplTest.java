package com.javaneta.sale.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.javaneta.sale.dto.ProductDto;
import com.javaneta.sale.dto.SaleDto;
import com.javaneta.sale.entity.ProductEntity;
import com.javaneta.sale.entity.SaleEntity;
import com.javaneta.sale.entity.SaleEntity.TypeMethodPayment;
import com.javaneta.sale.exceptions.ResourceNotFoundException;
import com.javaneta.sale.repository.SaleRepository;

@ExtendWith(MockitoExtension.class)
class SaleServiceImplTest {

    private static final Long idSale = (long) 1;
    private static final String idProduct = "111";
    private static final Long productId = (long) 5;
    private static final String description = "Rotula";
    private static final int quantity = 5;
    private static final double unitPrice = 123.99;
    private static final TypeMethodPayment typeMethodPayment = TypeMethodPayment.CASH;
    private static final String idCustomer = "8";
    private static final String currency = "Pesos";
    private static final Double totalAmount = 619.95;
    private static final Date date = new Date();
    private static final String idCard = "001";

    @Mock
    private SaleRepository repository;

    @InjectMocks
    private SaleServiceImpl saleService;

    private static List<ProductDto> productsListDto;
    private static SaleDto saleDto;
    private static List<ProductEntity> productsListEntity;
    private static SaleEntity saleEntity;

    @BeforeAll
    public static void setUp() throws Exception {

        productsListDto = new ArrayList<ProductDto>();
        productsListDto.add(ProductDto.builder().id(productId).productId(idProduct).description(description)
                .quantity(quantity).unitPrice(unitPrice).build());

        productsListEntity = new ArrayList<ProductEntity>();
        productsListEntity.add(ProductEntity.builder().id(productId).productId(idProduct).description(description)
                .quantity(quantity).unitPrice(unitPrice).build());

        saleDto = SaleDto.builder().id(idSale).currency(currency).dateSale(date).idCustomer(idCustomer)
                .typeMethodPayment(typeMethodPayment).idCard(idCard).totalAmount(totalAmount).products(productsListDto)
                .build();

        saleEntity = SaleEntity.builder().id(idSale).currency(currency).dateSale(date).idCustomer(idCustomer)
                .typeMethodPayment(typeMethodPayment).idCard(idCard).totalAmount(totalAmount)
                .products(productsListEntity).build();
    }

    @Test
    @DisplayName("Debe retornar todas las ventas")
    void getAllSaleTest() {
        var listSale = new ArrayList<SaleEntity>();

        listSale.add(SaleEntity.builder().id(idSale).currency(currency).dateSale(date).idCustomer(idCustomer)
                .typeMethodPayment(typeMethodPayment).idCard(idCard).totalAmount(totalAmount)
                .products(productsListEntity).build());
        listSale.add(SaleEntity.builder().id(idSale).currency(currency).dateSale(date).idCustomer(idCustomer)
                .typeMethodPayment(typeMethodPayment).idCard(idCard).totalAmount(totalAmount)
                .products(productsListEntity).build());

        when(repository.findAll()).thenReturn(listSale);
        assertEquals(2, saleService.getAllSales().size());
    }

    @Test
    @DisplayName("Al buscar una venta por su ID y no encontrarla, se lanzara una excepcion")
    void saleNotFoundTest() {
        when(repository.findById(idSale)).thenThrow(ResourceNotFoundException.class);
        assertThrowsExactly(ResourceNotFoundException.class, () -> saleService.getSaleId(idSale));
    }

    @Test
    @DisplayName("Debe retornar la misma informacion del ID que se busca")
    void getByIdSaleTest() {
        when(repository.findById(idSale)).thenReturn(Optional.of(saleEntity));
        var expectedEntity = saleEntity;
        var entityService = saleService.getSaleId(idSale);
        assertEquals(entityService, expectedEntity);
    }

    @Test
    void createSaleTest() {
        var saleDtoService = saleService.createSale(saleDto);
        assertThat(saleDtoService).isNotNull();
    }

    @Test
    @DisplayName("Debe retornar la lista de ventas que se le realizo a un determinado cliente")
    void searchByIdCustomerTest() throws Exception {
        var listSale = new ArrayList<SaleEntity>();

        listSale.add(SaleEntity.builder().id(idSale).currency(currency).dateSale(date).idCustomer(idCustomer)
                .typeMethodPayment(typeMethodPayment).idCard(idCard).totalAmount(totalAmount)
                .products(productsListEntity).build());
        listSale.add(SaleEntity.builder().id((long) 3).currency(currency).dateSale(date).idCustomer(idCustomer)
                .typeMethodPayment(typeMethodPayment).idCard(idCard).totalAmount(totalAmount)
                .products(productsListEntity).build());

        when(repository.searchByIdCustomer(idCustomer)).thenReturn(listSale);
        List<SaleEntity> saleList = saleService.searchByIdCustomer(idCustomer);
        assertThat(saleList).isNotEmpty();
    }
}
