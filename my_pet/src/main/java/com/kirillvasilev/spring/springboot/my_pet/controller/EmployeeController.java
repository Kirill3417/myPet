package com.kirillvasilev.spring.springboot.my_pet.controller;

import com.kirillvasilev.spring.springboot.my_pet.dao.EmployeeRepository;
import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Department;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;
import com.kirillvasilev.spring.springboot.my_pet.exception.NotFoundException;
import com.kirillvasilev.spring.springboot.my_pet.service.DepartmentService;
import com.kirillvasilev.spring.springboot.my_pet.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiEmp")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<EmployeeDto> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployee(@PathVariable int id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.saveEmployee(employeeDto);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto);
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);

        return "Employee with ID = " + id + " was deleted";
    }
}