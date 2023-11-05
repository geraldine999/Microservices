package com.globallogic.bff.service;

import com.globallogic.bff.client.SalesClient;
import com.globallogic.bff.dto.ProductReportDTO;
import com.globallogic.bff.dto.SalesDTO;
import com.globallogic.bff.dto.SoldProductDTO;
import com.globallogic.bff.dto.SoldProductsReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesClient salesClient;

    public SoldProductsReportDTO generateSoldProductsReport() {
        var sales = salesClient.getAllSales();

        List<SoldProductDTO> products = sales.stream().map(SalesDTO::getProducts).flatMap(List::stream)
                .collect(Collectors.toList());

        List<ProductReportDTO> productReportDTOs = new ArrayList<>();

        for (SoldProductDTO product : products) {
            int total = products.stream().filter(p -> p.getProductId().equals(product.getProductId()))
                    .map(SoldProductDTO::getQuantity).reduce(Integer::sum).orElse(0);

            ProductReportDTO dto = ProductReportDTO.builder().id(product.getProductId())
                    .description(product.getDescription()).totalUnitsSold(total).build();

            productReportDTOs.add(dto);
        }

        productReportDTOs = new ArrayList<>(new LinkedHashSet<>(productReportDTOs));

        productReportDTOs = productReportDTOs.stream()
                .sorted(Comparator.comparingInt(ProductReportDTO::getTotalUnitsSold).reversed()).limit(10)
                .collect(Collectors.toList());

        return new SoldProductsReportDTO(productReportDTOs);
    }

    public List<SalesDTO> getSales() {
        return salesClient.getAllSales();
    }

    public List<SalesDTO> getSalesByClient(Integer clientId) {
        return salesClient.getSalesByClient(clientId);
    }

    public SalesDTO createSale(SalesDTO salesDTO) {
        return salesClient.createSale(salesDTO);
    }
}
