package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dao.ClientRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.ClientDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import com.kirillvasilev.spring.springboot.my_pet.service.ClientService;
import com.kirillvasilev.spring.springboot.my_pet.service.DepartmentService;
import com.kirillvasilev.spring.springboot.my_pet.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiCli")
public class ClientController {

    private ClientService clientService;
    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private ClientRepository clientRepository;


    @Autowired
    public ClientController(ClientService clientService, EmployeeService employeeService, DepartmentService departmentService, ClientRepository clientRepository) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.clientRepository = clientRepository;
    }

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
        Client client = new Client();
        Employee employee = employeeService.findByName(clientDto.getEmployee().getName());
        Department department = departmentService.findByDepartmentName(clientDto.getEmployee().getDepartmentDto().getName());

        client.setName(clientDto.getName());

        if (employee != null && department != null) {
            employee.setDepartment(department);
            client.setEmployee(employee);
        } else {
            throw new NotFoundException("Choose another department and/or employee");
        }

        clientService.saveClient(client);
        return client;
    }

    @PutMapping("/clients")
    public Client updateEmployee(@RequestBody ClientDto clientDto) {
        Client client = clientRepository.findById(clientDto.getId()).orElseThrow(() -> new ObjenesisException("Id not found"));
        Employee employee = employeeService.findByName(clientDto.getEmployee().getName());

        if (employee != null) {
            client.setEmployee(employee);
        } else {
            throw new NotFoundException("Choose another employee");
        }
        client.setName(clientDto.getName());
        client.setEmployee(employee);
        clientService.saveClient(client);
        return client;
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable int id) {
        clientService.deleteClient(id);

        return "Client with ID = " + id + " was deleted";

    }

}

