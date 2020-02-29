package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="obavestenja")
public class ObavestenjaRepository {
	@Autowired
	ObavestenjaRepository or;

}
