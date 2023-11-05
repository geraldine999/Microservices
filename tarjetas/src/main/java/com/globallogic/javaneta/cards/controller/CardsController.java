package com.globallogic.javaneta.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.javaneta.cards.model.dto.CardsResponseDTO;
import com.globallogic.javaneta.cards.service.CardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final CardService service;

    @GetMapping("/cards/{cardNumber}/client/{clientId}")
    public ResponseEntity<CardsResponseDTO> getCards(@PathVariable String cardNumber, @PathVariable String clientId) {

        return new ResponseEntity<>(service.getCards(clientId, cardNumber), HttpStatus.OK);
    }

    @GetMapping("/cards/{cardNumber}")
    public ResponseEntity<CardsResponseDTO> getCardById(@PathVariable String cardNumber) {

        return new ResponseEntity<>(service.getCardsById(cardNumber), HttpStatus.OK);
    }

}
