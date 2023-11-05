package com.globallogic.javaneta.cards.service;

import com.globallogic.javaneta.cards.model.dto.CardsResponseDTO;

public interface CardService {
    
    public CardsResponseDTO getCards(String clientId, String cardNumber);

    public CardsResponseDTO getCardsById(String cardNumber);

}
