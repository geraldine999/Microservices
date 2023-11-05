package com.globallogic.clients.services.impl;

import org.springframework.stereotype.Service;

import com.globallogic.clients.dtos.ClientDTO;
import com.globallogic.clients.exceptions.NoClientException;
import com.globallogic.clients.services.ClientService;
import com.globallogic.clients.services.FeignClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final FeignClientService feign;

    @Override
    public ClientDTO getClientById(Integer id) {

        ClientDTO clientById = feign.getClientById(id);
        if (!clientById.getId().equals(id)) {
            log.error("Cliente con id" + "" + id + "" + "no encontrado");
            throw new NoClientException();
        }
        if (log.isInfoEnabled()) {
            log.info("Cliente encontrado: " + clientById.toString());
        }

        return clientById;
    }

    @Override
    public ClientDTO getClientByDni(Long dni) {
        ClientDTO clientByDni = feign.getClientByDni(dni);
        if (!clientByDni.getDni().equals(dni)) {
            log.error("Cliente con dni" + "" + dni + "" + "no encontrado");
            throw new NoClientException();
        }
        if (log.isInfoEnabled()) {
            log.info("Cliente encontrado: " + clientByDni.toString());
        }
        return clientByDni;
    }

}
