package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiCli")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public List<Client> showAllClients(){
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public Client getClient(@PathVariable int id){
        return clientService.getClient(id);
    }

    @PostMapping("/clients")
    public Client addNewClient(@RequestBody Client client){
        clientService.saveClient(client);
        return client;
    }

    @PutMapping("/clients")
    public Client updateEmployee(@RequestBody Client client){
        clientService.saveClient(client);
        return client;
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable int id){
        clientService.deleteClient(id);

        return "Client with ID = " + id + " was deleted";
    }
}
