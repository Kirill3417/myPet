package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.ClientRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.ClientDto;
import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void saveClient(Client client) {
        clientRepository.save(client);

    }

    @Override
    public ClientDto getClient(int id) {
        return clientRepository.findById(id)
                .map(cli -> {
                    EmployeeDto employeeDto = new EmployeeDto(cli.getEmployee().getId(), cli.getEmployee().getName());
                    return new ClientDto(cli.getId(), cli.getName(), employeeDto);
                })
                .orElseThrow();

    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}
