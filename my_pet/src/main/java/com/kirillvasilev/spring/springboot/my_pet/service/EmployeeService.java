package com.kirillvasilev.spring.springboot.my_pet.service;


import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<EmployeeDto> getAllEmployees();

    public Employee saveEmployee(EmployeeDto employeeDto);

    public Employee updateEmployee(EmployeeDto employeeDto);

    public EmployeeDto getEmployee(int id);

    public void deleteEmployee(int id);

    public Optional<Employee> findByName(String name);
}
