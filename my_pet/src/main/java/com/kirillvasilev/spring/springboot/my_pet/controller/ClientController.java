package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dto.ClientDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiCli")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/clients")
    public List<ClientDto> showAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable int id) {
        return clientService.getClient(id);
    }

    @PostMapping("/clients")
    public Client addNewClient(@RequestBody ClientDto clientDto) {
        return clientService.saveClient(clientDto);
    }

    @PutMapping("/clients")
    public Client updateEmployee(@RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientDto);
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return "Client with ID = " + id + " was deleted";

    }
}

