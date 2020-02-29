package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajrole;

public interface RoleRepository  extends JpaRepository<Ajrole,Integer>{
	Ajrole findByNaziv(String naziv);
}
