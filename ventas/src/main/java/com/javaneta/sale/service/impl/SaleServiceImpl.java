package com.javaneta.sale.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.javaneta.sale.dto.ProductDto;
import com.javaneta.sale.dto.SaleDto;
import com.javaneta.sale.entity.ProductEntity;
import com.javaneta.sale.entity.SaleEntity;
import com.javaneta.sale.exceptions.ResourceNotFoundException;
import com.javaneta.sale.repository.SaleRepository;
import com.javaneta.sale.service.SaleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;

    public SaleDto createSale(SaleDto sale) {

        if (log.isInfoEnabled()) {
            log.info("Comienzo de la registracion de una nueva venta");
        }

        final var currency = "Pesos";
        var dateHourSale = new Date();
        var totalAmount = 0.0;

        var productEntities = ProductDto.productDtosToEntities(sale.getProducts());
        for (ProductEntity i : productEntities) {
            totalAmount += i.getQuantity() * i.getUnitPrice();
        }
        totalAmount = formatDecimals(totalAmount, 2);

        var saleEntity = SaleEntity.builder().typeMethodPayment(sale.getTypeMethodPayment())
                .idCustomer(sale.getIdCustomer()).dateSale(dateHourSale).products(productEntities).currency(currency)
                .totalAmount(totalAmount).build();

        if (sale.getTypeMethodPayment().equals(SaleEntity.TypeMethodPayment.CARD)) {
            saleEntity.setIdCard(sale.getIdCard());
        }

        saleRepository.save(saleEntity);

        SaleDto dto = new SaleDto();
        BeanUtils.copyProperties(saleEntity, dto);
        dto.setProducts(sale.getProducts());
        return dto;
    }

    public List<SaleEntity> getAllSales() {
        var allSales = saleRepository.findAll();
        log.info("Listado de ventas " + allSales.toString());
        return allSales;
    }

    public SaleEntity getSaleId(Long id) {
        if (log.isInfoEnabled()) {
            log.info("Obteniendo el id de una venta");
        }
        var sale = this.findSaleById(id);
        return SaleEntity.builder().id(sale.getId()).typeMethodPayment(sale.getTypeMethodPayment())
                .idCard(sale.getIdCard()).idCustomer(sale.getIdCustomer()).dateSale(sale.getDateSale())
                .currency(sale.getCurrency()).totalAmount(sale.getTotalAmount()).products(sale.getProducts()).build();
    }

    public static Double formatDecimals(Double number, Integer numbersDecimals) {
        if (log.isInfoEnabled()) {
            log.info("Formatear el importe para que tenga dos decimales");
        }
        return Math.round(number * Math.pow(10, numbersDecimals)) / Math.pow(10, numbersDecimals);
    }

    private SaleEntity findSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro una venta con el id solicitado"));
    }

    public List<SaleEntity> searchByIdCustomer(String id) throws ResourceNotFoundException {
        if (log.isInfoEnabled()) {
            log.info("Busqueda de ventas por id de cliente " + id);
        }
        return saleRepository.searchByIdCustomer(id);
    }
}
