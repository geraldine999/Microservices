package com.globallogic.javaneta.cards.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.globallogic.javaneta.cards.wsdl.CardInput;
import com.globallogic.javaneta.cards.wsdl.Cards;

public class CardClient extends WebServiceGatewaySupport {

    private static final Logger LOG = LoggerFactory.getLogger(CardClient.class);

    public Cards getCards(String clientId) {

        CardInput request = new CardInput();
        request.setClientId(clientId);
        Cards response = (Cards) getWebServiceTemplate().marshalSendAndReceive("http://localhost:8094/mockGetCardsSoap",
                request, new SoapActionCallback("http://www.globallogic.com/talen-engine/card"));

        return response;
    }

}