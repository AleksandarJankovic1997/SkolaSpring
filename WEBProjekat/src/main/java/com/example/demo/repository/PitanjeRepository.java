package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ajpitanja;
import model.Ajtest;

public interface PitanjeRepository extends JpaRepository<Ajpitanja,Integer> {
	List<Ajpitanja> findByAjtest(Ajtest test);
	Ajpitanja findByPitanje(String s);
}
