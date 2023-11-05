package com.globallogic.clients.controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.clients.dtos.ClientDTO;
import com.globallogic.clients.exceptions.EmptyValueException;
import com.globallogic.clients.services.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {

    private final ClientService service;

    @GetMapping(value = { "/", "/{id}" })
    public ClientDTO clientById(@PathVariable("id") Integer id) {
        if (id == null) {
            throw new EmptyValueException();
        }
        return service.getClientById(id);
    }

    @GetMapping(value = { "/dni", "/dni/{dni}" })
    public ClientDTO clientByDni(@PathVariable("dni") Long dni) {
        if (dni == null) {
            throw new EmptyValueException();
        }
        return service.getClientByDni(dni);
    }

}
