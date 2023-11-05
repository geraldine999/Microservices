package com.globallogic.bff.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.globallogic.bff.dto.SalesDTO;

@FeignClient(name = "ventas", path = "${javaneta-services.base-path}")
public interface SalesClient {

    @GetMapping("/sales")
    List<SalesDTO> getAllSales();

    @PostMapping("/sales/")
    SalesDTO createSale(@RequestBody SalesDTO sale);

    @GetMapping("/sales/search")
    List<SalesDTO> getSalesByClient(@RequestParam Integer idCustomer);
}
