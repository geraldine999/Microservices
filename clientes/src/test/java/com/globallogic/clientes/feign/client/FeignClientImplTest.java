package com.globallogic.clientes.feign.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.globallogic.clients.dtos.ClientDTO;
import com.globallogic.clients.exceptions.NoClientException;
import com.globallogic.clients.services.FeignClientService;
import com.globallogic.clients.services.impl.ClientServiceImpl;

import utils.ClientTestUtil;

@ExtendWith(MockitoExtension.class)
class FeignClientImplTest {

    private static ClientDTO client;
    private static ClientDTO invalidClient;

    @Mock
    private FeignClientService feign;

    @InjectMocks
    private ClientServiceImpl clientService;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        client = ClientTestUtil.getClient();
        invalidClient = ClientTestUtil.getInvalidClient();

    }

    @Test
    void getClientByIdOkTest() {
        when(feign.getClientById(client.getId())).thenReturn(client);
        ClientDTO returnClient = clientService.getClientById(client.getId());
        assertThat(returnClient).isNotNull();
    }

    @Test
    void getClientByDniOkTest() {
        when(feign.getClientByDni(client.getDni())).thenReturn(client);
        ClientDTO returnClient = clientService.getClientByDni(client.getDni());
        assertThat(returnClient).isNotNull();
    }

    @Test
    void getClientByIdInvalidTest() {
        when(feign.getClientById(invalidClient.getId())).thenReturn(client);
        NoClientException thrown = assertThrows(NoClientException.class,
                () -> clientService.getClientById(invalidClient.getId()),
                "Expected getClientById to throw, but it didn't");
        assertSame(thrown.getClass(), NoClientException.class);
    }

    @Test
    void getClientByDniInvalidTest() {
        when(feign.getClientByDni(invalidClient.getDni())).thenReturn(client);
        NoClientException thrown = assertThrows(NoClientException.class,
                () -> clientService.getClientByDni(invalidClient.getDni()),
                "Expected getClientByDni to throw, but it didn't");
        assertSame(thrown.getClass(), NoClientException.class);
    }

}
