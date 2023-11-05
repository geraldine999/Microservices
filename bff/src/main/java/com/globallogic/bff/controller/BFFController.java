package com.globallogic.bff.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.bff.dto.CardsResponseDTO;
import com.globallogic.bff.dto.ClientDTO;
import com.globallogic.bff.dto.ClientSalesReportDto;
import com.globallogic.bff.dto.ProductDTO;
import com.globallogic.bff.dto.SalesDTO;
import com.globallogic.bff.dto.SoldProductsReportDTO;
import com.globallogic.bff.service.CardService;
import com.globallogic.bff.service.ClientService;
import com.globallogic.bff.service.ProductService;
import com.globallogic.bff.service.SalesService;
import com.globallogic.bff.service.ShoppingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BFFController {

    private final ProductService productService;
    private final CardService cardService;
    private final ClientService clientService;
    private final SalesService salesService;
    private final ShoppingService shoppingService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/cards/client/{clientId}/card/{cardNumber}")
    public ResponseEntity<CardsResponseDTO> getCards(@PathVariable String clientId, @PathVariable String cardNumber) {
        return new ResponseEntity<>(cardService.getCards(clientId, cardNumber), HttpStatus.OK);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Integer id) {
        return new ResponseEntity<>(clientService.clientById(id), HttpStatus.OK);
    }

    @GetMapping("/clients/dni/{dni}")
    public ResponseEntity<ClientDTO> getClientByDni(@PathVariable Long dni) {
        return new ResponseEntity<>(clientService.clientByDni(dni), HttpStatus.OK);
    }

    @GetMapping("products-report")
    public SoldProductsReportDTO generateSoldProductsReport() {
        return salesService.generateSoldProductsReport();
    }

    @GetMapping("sales")
    public List<SalesDTO> getSalesDto() {
        return salesService.getSales();
    }

    @PostMapping("/shop")
    public ResponseEntity<SalesDTO> createShop(@RequestBody @Valid SalesDTO sale) {
        return new ResponseEntity<>(shoppingService.createShop(sale), HttpStatus.CREATED);
    }

    @GetMapping("client-sales-report/client/{clientId}")
    public ClientSalesReportDto generateClientSalesReportDto(@PathVariable Integer clientId) {
        return clientService.generateClientSalesReport(clientId);
    }

    @GetMapping("client-sales-report/client/dni/{clientDni}")
    public ClientSalesReportDto generateClientSalesReportDto(@PathVariable Long clientDni) {
        return clientService.generateClientSalesReport(clientDni);
    }
}
