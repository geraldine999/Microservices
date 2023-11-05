package com.globallogic.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.globallogic.bff.dto.CardsDTO;
import com.globallogic.bff.dto.CardsResponseDTO;

@FeignClient(name = "tarjetas", path = "${javaneta-services.base-path}")
public interface CardsClient {

    @GetMapping("/cards/{cardNumber}/client/{clientId}")
    CardsResponseDTO getCards(@PathVariable String clientId, @PathVariable String cardNumber);

    @GetMapping("/cards/{idCard}")
    CardsDTO getCardById(@PathVariable String idCard);
}