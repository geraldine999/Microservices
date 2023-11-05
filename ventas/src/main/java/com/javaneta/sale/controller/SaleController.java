package com.javaneta.sale.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaneta.sale.dto.SaleDto;
import com.javaneta.sale.entity.SaleEntity;
import com.javaneta.sale.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleDto createSale(@RequestBody @Valid SaleDto sale) {
        return service.createSale(sale);
    }

    @GetMapping("")
    public List<SaleEntity> getAllSales() {
        return service.getAllSales();
    }

    @GetMapping("/{id}")
    public SaleEntity saleId(@PathVariable("id") Long id) {
        return service.getSaleId(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchByIdCustomer(@RequestParam("idCustomer") String idCustomer) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.searchByIdCustomer(idCustomer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error");
        }

    }
}
