package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajuser;

public interface AjuserRepository extends JpaRepository<Ajuser,Integer> {
	Ajuser findByUsername(String username);
}
