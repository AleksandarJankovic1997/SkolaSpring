package com.example.demo.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class main {
	public static void main(String[]args) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("profesor2"));
	}
}
