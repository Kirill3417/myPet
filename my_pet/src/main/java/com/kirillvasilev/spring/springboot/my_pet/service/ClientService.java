package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;

import java.util.List;

public interface ClientService{
    public List<Client> getAllClients();
    public void saveClient(Client client);
    public Client getClient(int id);
    public void deleteClient(int id);
}
