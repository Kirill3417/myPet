package com.kirillvasilev.spring.springboot.my_pet.dto;

import lombok.Value;


@Value
public class ClientDto {

     int id;

     String name;

     EmployeeDto employee;

}
