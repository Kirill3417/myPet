package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dto.ClientDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;

import java.util.List;

public interface ClientService {
    List<ClientDto> getAllClients();

    Client saveClient(ClientDto clientDto);

    Client updateClient(ClientDto clientDto);

    ClientDto getClient(int id);

    void deleteClient(int id);
}
