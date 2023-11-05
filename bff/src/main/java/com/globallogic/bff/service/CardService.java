package com.globallogic.bff.service;

import com.globallogic.bff.dto.CardsDTO;
import com.globallogic.bff.dto.MethodPaymentEnum;
import com.globallogic.bff.dto.SalesDTO;
import org.springframework.stereotype.Service;

import com.globallogic.bff.client.CardsClient;
import com.globallogic.bff.dto.CardsResponseDTO;

@Service
public class CardService {

    private final CardsClient client;

    public CardService(CardsClient client) {
        this.client = client;
    }

    public CardsResponseDTO getCards(String clientId, String cardNumber) {
        return client.getCards(clientId, cardNumber);
    }

    public CardsDTO getCardById(String idCard) {
        return client.getCardById(idCard);
    }

    public void validateCardIfNecessary(SalesDTO sale) {
        if (sale.getTypeMethodPayment().equals(MethodPaymentEnum.CARD)) {
            getCards(sale.getIdCustomer().toString(), sale.getIdCard());
        }
    }
}
