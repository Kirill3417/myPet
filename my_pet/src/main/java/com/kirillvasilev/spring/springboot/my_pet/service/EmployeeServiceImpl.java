package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.EmployeeRepository;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Employee employees = null;
        Optional<Employee> optional = employeeRepository.findById(id);
        if(optional.isPresent()){
            employees = optional.get();
        }
        return employees;
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
