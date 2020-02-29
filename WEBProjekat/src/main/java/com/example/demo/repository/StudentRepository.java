package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Ajpredmet;
import model.Ajstudent;

public interface StudentRepository extends JpaRepository<Ajstudent,Integer>{
	Ajstudent findByUsername(String username);

}
