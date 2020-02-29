package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajodgovor;
import model.Ajpredmet;
import model.Ajstudent;

public interface OdgovorRepository extends JpaRepository<Ajodgovor,Integer> {
	List<Ajodgovor> findByAjpredmetAndAjstudent(Ajpredmet p, Ajstudent s);
	List<Ajodgovor> findByAjstudent(Ajstudent s);
}
