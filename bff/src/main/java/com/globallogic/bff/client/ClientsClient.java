package com.globallogic.bff.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.globallogic.bff.dto.ClientDTO;

@FeignClient(name = "clientes", path = "${javaneta-services.base-path}")
public interface ClientsClient {

    @GetMapping("/clients/{id}")
    ClientDTO clientById(@PathVariable("id") Integer id);

    @GetMapping("/clients/dni/{dni}")
    ClientDTO clientByDni(@PathVariable("dni") Long dni);
}