package com.globallogic.javaneta.cards.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.javaneta.cards.exception.InvalidCardException;
import com.globallogic.javaneta.cards.exception.NoCardException;
import com.globallogic.javaneta.cards.model.dto.CardsResponseDTO;
import com.globallogic.javaneta.cards.service.impl.CardServiceImpl;
import com.globallogic.javaneta.cards.utils.TestUtils;
import com.globallogic.javaneta.cards.webservice.CardClient;
import com.globallogic.javaneta.cards.wsdl.Card;
import com.globallogic.javaneta.cards.wsdl.Cards;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {

    private static TestUtils utils;

    @Mock
    private CardClient cardClient;

    @InjectMocks
    private CardServiceImpl cardService;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        utils = TestUtils.builder().build();

        utils.setClientId("7");

        utils.setCardNumberOk("1254785698523654");
        utils.setCardNumberInvalid("1235235234");

        var cards = new Cards();
        var card = new Card();
        card.setClientId(utils.getClientId());
        card.setCardNumber(utils.getCardNumberOk());
        cards.getCard().add(card);
        utils.setCard(card);
        utils.setCards(cards);
    }

    @Test
    void getCardOkTest() {
        when(cardClient.getCards(utils.getClientId())).thenReturn(utils.getCards());
        CardsResponseDTO returnValue = cardService.getCards(utils.getClientId(), utils.getCardNumberOk());
        assertThat(returnValue).isNotNull();
    }

    @Test
    void getCardByIdOkTest() {
        when(cardClient.getCards("1")).thenReturn(utils.getCards());
        CardsResponseDTO returnValue = cardService.getCardsById(utils.getCardNumberOk());
        assertThat(returnValue).isNotNull();
    }

    @Test
    void getCardInvalidTest() {
        when(cardClient.getCards(utils.getClientId())).thenReturn(utils.getCards());
        String clientId = utils.getClientId();
        String cardInvalidNumber = utils.getCardNumberInvalid();
        InvalidCardException thrown = assertThrows(InvalidCardException.class,
                () -> cardService.getCards(clientId, cardInvalidNumber), "Expected getCards to throw, but it didn't");

        assertEquals(thrown.getClass(), InvalidCardException.class);
    }
    
    @Test
    void getNoCardTest() {
        var cards = new Cards();
        when(cardClient.getCards(utils.getClientId())).thenReturn(cards);
        String clientId = utils.getClientId();
        String cardInvalidNumber = utils.getCardNumberInvalid();
        NoCardException thrown = assertThrows(NoCardException.class,
                () -> cardService.getCards(clientId, cardInvalidNumber), "Expected getCards to throw, but it didn't");

        assertEquals(thrown.getClass(), NoCardException.class);
    }

    @Test
    void getCardByIdInvalidTest() {
        String cardInvalidNumber = utils.getCardNumberInvalid();
        when(cardClient.getCards("1")).thenReturn(utils.getCards());
        NoCardException thrown = assertThrows(NoCardException.class, () -> cardService.getCardsById(cardInvalidNumber),
                "Expected getCards to throw, but it didn't");

        assertEquals(thrown.getClass(), NoCardException.class);
    }

    @Test
    void getClientIdTest() {
        var clientId = "7";
        assertEquals(clientId, utils.getClientId());
    }

    @Test
    void getCardNumberOkTest() {
        var cardNumerOk = "1254785698523654";
        assertEquals(cardNumerOk, utils.getCardNumberOk());
    }

    @Test
    void getCardNumberInvalidTest() {
        var cardNumberInvalid = "1235235234";
        assertEquals(cardNumberInvalid, utils.getCardNumberInvalid());
    }

    @Test
    void getCardTest() {
        var card = new Card();
        card.setClientId(utils.getClientId());
        card.setCardNumber(utils.getCardNumberOk());
        utils.setCard(card);
        assertEquals(card, utils.getCard());
    }

    @Test
    void getCardsTest() {
        var cards = new Cards();
        var card = new Card();
        card.setClientId(utils.getClientId());
        card.setCardNumber(utils.getCardNumberOk());
        cards.getCard().add(card);
        utils.setCards(cards);
        assertEquals(cards, utils.getCards());
    }
}
