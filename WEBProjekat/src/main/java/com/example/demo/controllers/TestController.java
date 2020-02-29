package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.AsistentRepository;
import com.example.demo.repository.ObavestenjeRepository;
import com.example.demo.repository.OdgovorRepository;
import com.example.demo.repository.PitanjeRepository;
import com.example.demo.repository.PredmetRepository;
import com.example.demo.repository.ProfesorRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestRepository;

import model.Ajasistent;
import model.Ajobavestenje;
import model.Ajodgovor;
import model.Ajpitanja;
import model.Ajpredmet;
import model.Ajprofesor;
import model.Ajstudent;
import model.Ajtest;

@Controller
@RequestMapping(value="/korisnik")
public class TestController {
	@Autowired
	ObavestenjeRepository obr;
	@Autowired
	ProfesorRepository pr;
	@Autowired
	StudentRepository sr;
	@Autowired
	AsistentRepository ar;
	@Autowired
	PitanjeRepository pir;
	@Autowired
	OdgovorRepository or;
	@Autowired
	PredmetRepository prr;
	@Autowired
	TestRepository tr;
	@RequestMapping(value="/prikazPredmeta", method=RequestMethod.GET)
	public String prikaziPredmet(String idPredmet,HttpServletRequest request,Model m) {
		int id=Integer.parseInt(idPredmet.substring(1,2));
		Ajpredmet p=prr.findById(id).get();
		Ajprofesor profa=p.getAjprofesor();
		Ajasistent a=p.getAjasistent();
		 
		request.getSession().setAttribute("profesor", profa.getIme()+" "+profa.getPrezime());
		request.getSession().setAttribute("asistent",a.getIme()+" "+a.getPrezime());
		request.getSession().setAttribute("predmet", p);
		List<Ajobavestenje> lista=p.getAjobavestenjes();
		m.addAttribute("obavestenja", lista);
		if(request.isUserInRole("student")) {
			List<Ajtest> testovi=tr.findByAjpredmet(p);
			request.getSession().setAttribute("testovi", testovi);
		}
		else {
			List<Ajstudent> studenti=p.getAjstudents();
			request.getSession().setAttribute("studenti", studenti);
		}
		
		return "predmet";
	}
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
	@RequestMapping(value="ubaciObavestenje", method=RequestMethod.POST)
	public String ubaciObavestenje(String tekst,int predmet,Model m) {
		Ajpredmet p=prr.findById(predmet).get();
		List<Ajobavestenje> lista=obr.findByAjpredmet(p);
		m.addAttribute("obavestenja", lista);
		if(tekst.equalsIgnoreCase("")||tekst==null) {
			return "predmet";
		}
		Ajobavestenje o=new Ajobavestenje();
		o.setAjpredmet(p);
		o.setTekst(tekst);
		Date date=new Date();
		o.setDatum(date);
		obr.save(o);
		lista=obr.findByAjpredmet(p);
		m.addAttribute("obavestenja", lista);
		return "predmet";
	}
	
	@RequestMapping(value="ubacitest",method=RequestMethod.POST)
	public String novitest(Model m,int predmet,String imetesta, String p1,String p2,String p3, String p4,String p5,HttpServletRequest request) {
		Ajpredmet p=prr.findById(predmet).get();
		Ajtest test=new Ajtest();
		test.setImeTesta(imetesta);
		test.setAjpredmet(p);
		tr.save(test);
		Ajpitanja pi=new Ajpitanja();
		pi.setPitanje(p1);pi.setBrojPoena(1);pi.setAjtest(test);
		pir.save(pi); 	
		pi=new Ajpitanja();
		pi.setPitanje(p2);pi.setBrojPoena(1);pi.setAjtest(test);
		pir.save(pi);
		pi=new Ajpitanja();
		pi.setPitanje(p3);pi.setBrojPoena(1);pi.setAjtest(test);
		pir.save(pi);
		pi=new Ajpitanja();
		pi.setPitanje(p4);pi.setBrojPoena(1);pi.setAjtest(test);
		pir.save(pi);
		pi=new Ajpitanja();
		pi.setPitanje(p5);pi.setBrojPoena(1);pi.setAjtest(test);
		pir.save(pi);
		request.getSession().setAttribute("predmet", p);
		m.addAttribute("obavestenja", obr.findByAjpredmet(p));
		return "predmet";
	}
	@RequestMapping(value="radiTest", method=RequestMethod.GET)
	public String radiTest(int idSemaTest,Model m,HttpServletRequest request) {		
		Ajtest t=tr.findById(idSemaTest).get();
		List<Ajpitanja> lista=pir.findByAjtest(t);
		request.getSession().setAttribute("pitanja", lista);
		request.getSession().setAttribute("imetesta", t.getImeTesta());
		//List<Ajpitanja> pitanja=(List<Ajpitanja>) request.getSession().getAttribute("pitanja");
		m.addAttribute("pitanje",lista.get(0));
		m.addAttribute("broj", 0);
		return "test";
	}
	
	@RequestMapping(value="sledecePitanje",method=RequestMethod.GET)
	public String sledece(int broj,HttpServletRequest request,Model m,String odgovor) {
	//	System.out.println(pitanje);
		//int pit=Integer.parseInt(pitanje);
		Ajodgovor o=new Ajodgovor();
		o.setOdgovor(odgovor);
		o.setAjpitanja(((List<Ajpitanja>)request.getSession().getAttribute("pitanja")).get(broj-1));
		o.setAjpredmet((Ajpredmet)request.getSession().getAttribute("predmet"));
		Ajstudent s=(Ajstudent)request.getSession().getAttribute("osoba");
		o.setAjstudent(s);
		or.save(o);
		if(broj==5) {
			Ajpredmet p=(Ajpredmet)request.getSession().getAttribute("predmet");
			m.addAttribute("obavestenja", p.getAjobavestenjes());
			m.addAttribute("porukatest", "uspesno ste odradili test "+(String)request.getSession().getAttribute("imetesta"));
			return "predmet";
		}
		m.addAttribute("pitanje", ((List<Ajpitanja>)request.getSession().getAttribute("pitanja")).get(broj));
		m.addAttribute("broj",broj);
		return "test";
	}
	@RequestMapping(value="sviStudentiPredmet",method=RequestMethod.POST)
	public String studenti(String predmet,Model m) {
		Ajpredmet p=prr.findById(Integer.parseInt(predmet)).get();
		List<Ajstudent> lista=p.getAjstudents();
		m.addAttribute("predmet", p.getNaziv());
		m.addAttribute("studenti", lista);
		
		return "spisak";
	}
	@RequestMapping(value="pregledajtestove",method=RequestMethod.GET)
	public String pregledajtestove(int ids,String imep,HttpServletRequest request,Model m) {
		Ajstudent s=sr.findById(ids).get();
		Ajpredmet p=prr.findByNaziv("imep");
		List<Ajodgovor> odgovori=or.findByAjpredmetAndAjstudent(p,s);
		List<Ajpitanja> pitanja=new ArrayList();
		m.addAttribute("odgovori", odgovori);
		for(Ajodgovor o:odgovori) {
			pitanja.add(o.getAjpitanja());
		}
		List<Ajtest> testovi=tr.findByAjpredmet(p);
		request.getSession().setAttribute("testovi", testovi);
		request.getSession().setAttribute("pitanja", pitanja);
		request.getSession().setAttribute("odgovori", odgovori);
		m.addAttribute("broj", 0);
		m.addAttribute("test", testovi.get(0));
		m.addAttribute("pitanja", pitanja.subList(0, 5));
		m.addAttribute("odgovori", odgovori.subList(0, 5));
		return "pregledtesta";
	}
	
	@RequestMapping(value="sledecitest",method=RequestMethod.POST)
	public String sledeci(String odg1, String odg2, String odg3, String odg4,String odg5, int broj,HttpServletRequest request,Model m) {
		List<Ajodgovor> lista=(List<Ajodgovor>)request.getSession().getAttribute("odgovori");
		
		int i=(broj-1)*5;
		lista.get(i).setTacnost(odg1.equalsIgnoreCase("tacno")?0:1);
		or.save(lista.get(i));
		lista.get(++i).setTacnost(odg2.equalsIgnoreCase("tacno")?0:1);
		or.save(lista.get(i));
		lista.get(++i).setTacnost(odg3.equalsIgnoreCase("tacno")?0:1);
		or.save(lista.get(i));
		lista.get(++i).setTacnost(odg4.equalsIgnoreCase("tacno")?0:1);
		or.save(lista.get(i));
		lista.get(++i).setTacnost(odg5.equalsIgnoreCase("tacno")?0:1);
		or.save(lista.get(i));
		
		if(lista.get(broj*5)==null) {
			return "spisak.jsp";
		}
		m.addAttribute("test", ((List<Ajtest>)request.getSession().getAttribute("testovi")).get(broj));
		m.addAttribute("pitanja",((List<Ajpitanja>)request.getSession().getAttribute("pitanja")).subList(broj*5, broj*5+5));
		m.addAttribute("odgovori", ((List<Ajodgovor>)request.getSession().getAttribute("odgovori")).subList(broj*5, broj*5+5));
		return "pregledtesta.jsp";
	}
}
