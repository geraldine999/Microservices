package com.javaneta.sale.service;

import java.util.List;

import com.javaneta.sale.dto.SaleDto;
import com.javaneta.sale.entity.SaleEntity;
import com.javaneta.sale.exceptions.ResourceNotFoundException;

public interface SaleService {

    public List<SaleEntity> getAllSales();

    public SaleDto createSale(SaleDto sale);

    public SaleEntity getSaleId(Long id);

    List<SaleEntity> searchByIdCustomer(String id) throws ResourceNotFoundException;
}
