package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.ClientRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.ClientDto;
import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(cli -> {
                    DepartmentDto departmentDto = new DepartmentDto(cli.getEmployee().getDepartment().getId(), cli.getEmployee().getDepartment().getDepartmentName());
                    EmployeeDto employeeDto = new EmployeeDto(cli.getEmployee().getId(), cli.getEmployee().getName(), departmentDto);
                    return new ClientDto(cli.getId(), cli.getName(), employeeDto);
                }).collect(Collectors.toList());
    }

    @Override
    public Client saveClient(ClientDto clientDto) {
        Client client = new Client();
        Employee employee = employeeService.findByName(clientDto.getEmployee().getName()).orElseThrow(() -> new NotFoundException("Not found"));
        Department department = departmentService.findByDepartmentName(clientDto.getEmployee().getDepartmentDto().getName())
                .orElseThrow(() -> new NotFoundException("Not found"));

        employee.setDepartment(department);
        client.setEmployee(employee);
        client.setName(clientDto.getName());

        clientRepository.save(client);
        return client;
    }

    @Override
    public Client updateClient(ClientDto clientDto) {
        Client client = clientRepository.findById(clientDto.getId()).orElseThrow(() -> new NotFoundException("Not found"));
        Employee employee = employeeService.findByName(clientDto.getEmployee().getName()).orElseThrow(() -> new NotFoundException("Not found"));
        Department department = departmentService.findByDepartmentName(clientDto.getEmployee().getDepartmentDto().getName())
                .orElseThrow(() -> new NotFoundException("Not found"));

        employee.setDepartment(department);
        client.setEmployee(employee);
        client.setName(clientDto.getName());

        clientRepository.save(client);
        return client;
    }

    @Override
    public ClientDto getClient(int id) {
        return clientRepository.findById(id)
                .map(cli -> {
                    DepartmentDto departmentDto = new DepartmentDto(cli.getEmployee().getDepartment().getId(), cli.getEmployee().getDepartment().getDepartmentName());
                    EmployeeDto employeeDto = new EmployeeDto(cli.getEmployee().getId(), cli.getEmployee().getName(), departmentDto);
                    return new ClientDto(cli.getId(), cli.getName(), employeeDto);
                })
                .orElseThrow(() -> new NotFoundException("Id not found"));

    }

    @Override
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

}