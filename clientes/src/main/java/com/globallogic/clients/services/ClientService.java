package com.globallogic.clients.services;

import com.globallogic.clients.dtos.ClientDTO;

public interface ClientService {

    public ClientDTO getClientById(Integer id);

    public ClientDTO getClientByDni(Long dni);
}
