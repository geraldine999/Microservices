package com.globallogic.clients.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.globallogic.clients.dtos.ClientDTO;

@FeignClient(name = "feign-client", url = "${match-service.url}")
public interface FeignClientService {

    @GetMapping("/id/{id}")
    public ClientDTO getClientById(@PathVariable("id") Integer id);

    @GetMapping("/dni/{dni}")
    public ClientDTO getClientByDni(@PathVariable("dni") Long dni);

}
