package com.globallogic.bff.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.globallogic.bff.client.ClientsClient;
import com.globallogic.bff.dto.CardResponseDto;
import com.globallogic.bff.dto.CardsDTO;
import com.globallogic.bff.dto.ClientDTO;
import com.globallogic.bff.dto.ClientSalesReportDto;
import com.globallogic.bff.dto.MethodPaymentEnum;
import com.globallogic.bff.dto.SalesDTO;
import com.globallogic.bff.dto.SalesResponseDto;

@Service
public class ClientService {

    private final ClientsClient clientsClient;
    private final SalesService salesService;
    private final CardService cardService;

    public ClientService(ClientsClient clientsClient, SalesService salesService, CardService cardService) {
        this.clientsClient = clientsClient;
        this.salesService = salesService;
        this.cardService = cardService;
    }

    public ClientDTO clientById(Integer id) {
        return clientsClient.clientById(id);
    }

    public ClientDTO clientByDni(Long dni) {
        return clientsClient.clientByDni(dni);
    }

    public ClientSalesReportDto generateClientSalesReport(Object dniOrId) {

        ClientDTO client = getClient(dniOrId);

        List<SalesDTO> sales = salesService.getSalesByClient(client.getId());

        List<SalesResponseDto> salesResponseDtos = getSalesResponseDtos(sales);

        return ClientSalesReportDto.builder().address(client.getAddress()).fullName(client.getFullName())
                .dni(client.getDni()).id(client.getId()).sales(salesResponseDtos).build();
    }

    private List<SalesResponseDto> getSalesResponseDtos(List<SalesDTO> sales) {
        List<SalesResponseDto> salesResponseDtos = new ArrayList<>();
        for (SalesDTO s : sales) {
            salesResponseDtos.add(SalesResponseDto.builder().date(s.getDateSale())
                    .methodPayment(s.getTypeMethodPayment()).totalAmount(s.getTotalAmount()).items(s.getProducts())
                    .card(getSaleCardIfNecessary(s)).build());
        }

        return salesResponseDtos;
    }

    private ClientDTO getClient(Object dniOrId) {
        var client = new ClientDTO();
        if (dniOrId.getClass().equals(Long.class)) {
            client = clientByDni((Long) dniOrId);
        }
        if (dniOrId.getClass().equals(Integer.class)) {
            client = clientById((Integer) dniOrId);
        }
        return client;
    }

    private CardResponseDto getSaleCardIfNecessary(SalesDTO sale) {
        if (sale.getTypeMethodPayment().equals(MethodPaymentEnum.CASH)) {
            return null;
        } else {
            CardsDTO card = cardService.getCardById(sale.getIdCard());
            return CardResponseDto.builder().issuer(card.getCompany()).number(card.getNumber()).build();
        }

    }

}
