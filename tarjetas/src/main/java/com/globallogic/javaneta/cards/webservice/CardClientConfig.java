package com.globallogic.javaneta.cards.webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CardClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.globallogic.javaneta.cards.wsdl");
        return marshaller;
    }

    @Bean
    public CardClient cardClient(Jaxb2Marshaller marshaller) {
        CardClient client = new CardClient();
        client.setDefaultUri("http://localhost:8094");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
