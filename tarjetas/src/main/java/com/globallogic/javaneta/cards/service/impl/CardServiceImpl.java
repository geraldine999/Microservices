package com.globallogic.javaneta.cards.service.impl;

import org.springframework.stereotype.Service;

import com.globallogic.javaneta.cards.exception.InvalidCardException;
import com.globallogic.javaneta.cards.exception.NoCardException;
import com.globallogic.javaneta.cards.model.dto.CardsResponseDTO;
import com.globallogic.javaneta.cards.service.CardService;
import com.globallogic.javaneta.cards.webservice.CardClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardClient quoteClient;

    public CardsResponseDTO getCards(String clientId, String cardNumber) {

        if (log.isInfoEnabled()) {
            log.info("Get card by clientId: " + clientId);
            log.info("With number: " + cardNumber);
        }

        var cards = quoteClient.getCards(clientId);

        var resultCard = cards.getCard().stream().filter(card -> clientId.equals(card.getClientId())).findAny()
                .orElse(null);

        if (resultCard == null) {
            log.error("No se encontró la tarjeta.");
            throw new NoCardException();
        } else if (!resultCard.getCardNumber().equalsIgnoreCase(cardNumber)) {
            log.error("La tarjeta no concuerda con la tarjeta del cliente.");
            throw new InvalidCardException();
        }

        var response = new CardsResponseDTO(resultCard.getCardNumber(), resultCard.getClientId(),
                resultCard.getCardIssuer());

        if (log.isInfoEnabled()) {
            log.info("Card obtained: " + response.toString());
        }

        return response;
    }

    public CardsResponseDTO getCardsById(String cardNumber) {
        if (log.isInfoEnabled()) {
            log.info("With number: " + cardNumber);
        }

        var cards = quoteClient.getCards("1");

        var resultCard = cards.getCard().stream().filter(card -> cardNumber.equals(card.getCardNumber())).findAny()
                .orElse(null);

        if (resultCard == null) {
            log.error("No se encontró la tarjeta.");
            throw new NoCardException();
        }

        var response = new CardsResponseDTO(resultCard.getCardNumber(), resultCard.getClientId(),
                resultCard.getCardIssuer());

        if (log.isInfoEnabled()) {
            log.info("Card obtained: " + response.toString());
        }

        return response;
    }

}
