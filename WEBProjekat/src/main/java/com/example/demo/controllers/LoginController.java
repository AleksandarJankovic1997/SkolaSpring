 package com.example.demo.controllers;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.AsistentRepository;
import com.example.demo.repository.PredmetRepository;
import com.example.demo.repository.ProfesorRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.UserDetailsImpl;

import model.Ajasistent;
import model.Ajprofesor;
import model.Ajrole;
import model.Ajstudent;
import model.Ajuser;

@Controller
@RequestMapping(value="/security")
public class LoginController {
	
	@Autowired
	RoleRepository rr;
	@Autowired
	UserRepository ur;
	@Autowired
	ProfesorRepository pr;
	@Autowired
	AsistentRepository ar;
	@Autowired
	StudentRepository sr;
	@Autowired
	PredmetRepository prr;
	
	@RequestMapping(value="greska", method=RequestMethod.GET)
	public String greska(Model m) {
		m.addAttribute("greska",true);
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(String error,Model m) {
		if(error!=null){	
			m.addAttribute("greska", "greska");
		}
		return "login";
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout() {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/security/login";
	}
	
	@RequestMapping(value="saDSADAveUser",method=RequestMethod.POST)
	public String save() {
		System.out.println("1");
		return "novikorisnik";
	}
	
	@RequestMapping(value="/saveUser", method=RequestMethod.GET)
	public String saveUser(String username,String password,String uloga,String ime, String prezime, String brindeksa) {
		System.out.println("1");
		Ajuser user=new Ajuser();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		user.setUsername(username);
		System.out.println("2");
		Ajrole r=rr.findByNaziv("uloga");
		user.getAjroles().add(r);
		user.setUloga("a");
		ur.save(user);
		System.out.println("3");
		if(uloga.equalsIgnoreCase("student")) {
			Ajstudent s=new Ajstudent();
			s.setBrojIndeksa(Integer.parseInt(brindeksa));
			s.setIme(ime);
			s.setPrezime(prezime);
			s.setUsername(username);
			Date d=new Date();
			s.setDatumR(d);
			sr.save(s);
		}
		System.out.println("4");
		return "novkorisnik";
	}
	
	@RequestMapping(value="/profil",method=RequestMethod.GET)
	public String profil(HttpServletRequest request,Principal principal	) {
		if(request.isUserInRole("administrator")) {
			return "novkorisnik";
		}
		UserDetailsImpl user=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Ajuser currentUser=ur.findByUsername(user.getUsername());
		if(currentUser.getUloga().equalsIgnoreCase("p")) {
			Ajprofesor p=pr.findByUsername(currentUser.getUsername());
			request.getSession().setAttribute("osoba",p) ;
			request.getSession().setAttribute("predmeti", prr.findByAjprofesor(p));
		}
		else if(currentUser.getUloga().equalsIgnoreCase("a")) {
			Ajasistent a=ar.findByUsername(currentUser.getUsername());
			request.getSession().setAttribute("osoba",a);
			request.getSession().setAttribute("predmeti", prr.findByAjasistent(a));
		}
		else {
			Ajstudent s= sr.findByUsername(currentUser.getUsername());
			request.getSession().setAttribute("osoba",s);
			request.getSession().setAttribute("predmeti", s.getAjpredmets());
		}
		return "profil";
	}
}
