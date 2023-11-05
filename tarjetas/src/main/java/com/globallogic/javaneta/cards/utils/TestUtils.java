package com.globallogic.javaneta.cards.utils;

import com.globallogic.javaneta.cards.wsdl.Card;
import com.globallogic.javaneta.cards.wsdl.Cards;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestUtils {

    private String clientId;

    private String cardNumberOk;

    private String cardNumberInvalid;

    private Cards cards;

    private Card card;

}
