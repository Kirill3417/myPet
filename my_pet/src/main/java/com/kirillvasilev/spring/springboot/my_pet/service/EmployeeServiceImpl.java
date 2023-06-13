package com.kirillvasilev.spring.springboot.my_pet.service;

import com.kirillvasilev.spring.springboot.my_pet.dao.EmployeeRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.DepartmentDto;
import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;


    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(emp -> new EmployeeDto(emp.getId(), emp.getName(), new DepartmentDto(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName())))
                .collect(Collectors.toList());
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        Department department = departmentService.findByDepartmentName(employeeDto.getDepartmentDto().getName())
                .orElseThrow(() -> new NotFoundException("Id not found"));

        employee.setDepartment(department);
        employee.setName(employeeDto.getName());

        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new NotFoundException("Employee with this id is not found, please choose another employee"));
        Department department = departmentService.findByDepartmentName(employeeDto.getDepartmentDto().getName())
                .orElseThrow(() -> new NotFoundException("Department with this name is not found, please choose another department"));

        employee.setDepartment(department);
        employee.setName(employeeDto.getName());

        employeeRepository.save(employee);
        return employee;
    }


    @Override
    public EmployeeDto getEmployee(int id) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    DepartmentDto departmentDto = new DepartmentDto(emp.getDepartment().getId(), emp.getDepartment().getDepartmentName());
                    return new EmployeeDto(emp.getId(), emp.getName(), departmentDto);
                })
                .orElseThrow(() -> new NotFoundException("Employee with this id is not found, please choose another employee"));
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }
}
