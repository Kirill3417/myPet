package com.kirillvasilev.spring.springboot.my_pet.service;


import com.kirillvasilev.spring.springboot.my_pet.dao.ClientRepository;
import com.kirillvasilev.spring.springboot.my_pet.dao.DepartmentRepository;
import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);

    }

    @Override
    public Client getClient(int id) {
        Client client = null;
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isPresent()) {
            client = optional.get();
        }
        return client;
    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}
