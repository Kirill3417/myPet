package com.kirillvasilev.spring.springboot.my_pet.service;




import com.kirillvasilev.spring.springboot.my_pet.dto.EmployeeDto;
import com.kirillvasilev.spring.springboot.my_pet.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDto> getAllEmployees();
    public void saveEmployee(Employee employee);
    public EmployeeDto getEmployee(int id);
    public void deleteEmployee(int id);
}
