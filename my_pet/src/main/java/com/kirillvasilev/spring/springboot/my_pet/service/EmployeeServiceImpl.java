package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.EmployeeRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(emp -> new EmployeeDto(emp.getId(), emp.getName(), new DepartmentDto(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName())))
                .collect(Collectors.toList());
    }


    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeDto getEmployee(int id) {
        return employeeRepository.findById(id)
                .map(emp-> {
                    DepartmentDto departmentDto = new DepartmentDto(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName());
                    return new EmployeeDto(emp.getId(), emp.getName(), departmentDto);
                })
                .orElseThrow(()-> new NotFoundException("Id not found"));
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
    public Employee findByName(String name){
        Employee employee = employeeRepository.findByName(name);
        return employee;
    }
}
