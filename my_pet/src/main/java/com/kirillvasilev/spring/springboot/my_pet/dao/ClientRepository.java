package com.kirillvasilev.spring.springboot.my_pet.dao;

import com.kirillvasilev.spring.springboot.my_pet.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
