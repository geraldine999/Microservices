package com.globallogic.bff.service;

import com.globallogic.bff.dto.SalesDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShoppingService {

    private final ClientService clientService;
    private final ProductService productService;
    private final CardService cardService;
    private final SalesService saleService;

    public SalesDTO createShop(SalesDTO sale) {

        clientService.clientById(sale.getIdCustomer());
        cardService.validateCardIfNecessary(sale);
        productService.updateProductsStock(sale);

        var verifiedSale = SalesDTO.builder().typeMethodPayment(sale.getTypeMethodPayment())
                .idCustomer(sale.getIdCustomer()).idCard(sale.getIdCard()).products(sale.getProducts()).build();

        return saleService.createSale(verifiedSale);
    }



}
