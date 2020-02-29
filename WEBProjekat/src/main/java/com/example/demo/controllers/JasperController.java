package com.example.demo.controllers;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.repository.OdgovorRepository;
import com.example.demo.repository.PredmetRepository;
import com.example.demo.repository.ProfesorRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TestRepository;

import model.Ajodgovor;
import model.Ajpredmet;
import model.Ajprofesor;
import model.Ajtest;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping(value="reportsC")
public class JasperController {
	@Autowired
	OdgovorRepository or;
	@Autowired
	ProfesorRepository prr;
	@Autowired
	StudentRepository sr;
	@Autowired
	PredmetRepository pr;
	@Autowired
	TestRepository tr;
	@RequestMapping(value="sviStudentiPredmet", method=RequestMethod.GET)
	public void showReport1(HttpServletResponse response,String predmet) throws Exception{
		
		response.setContentType("text/html");
		Ajpredmet p=pr.findById(Integer.parseInt(predmet)).get();
		Ajprofesor profa=p.getAjprofesor();
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(p.getAjstudents());
		InputStream inputStream=this.getClass().getResourceAsStream("/jasperreports/sviStudenti.jrxml");
		JasperReport report=JasperCompileManager.compileReport(inputStream);//greska
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("predmet", p.getNaziv());
		params.put("profesor",profa.getIme()+" "+profa.getPrezime());

		JasperPrint jasperPrint=JasperFillManager.fillReport(report,params,dataSource);
		inputStream.close();
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment;filename=SviStudenti.pdf");
		OutputStream out=response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);
	}
	@RequestMapping(value="sviTestoviPredmeta", method=RequestMethod.GET)
	public void showReport2(HttpServletResponse response,String predmet) throws Exception{
		response.setContentType("text/html");
		Ajpredmet p=pr.findById(Integer.parseInt(predmet)).get();
		Ajprofesor profa=p.getAjprofesor();
		List<Ajtest> testovi=p.getAjtests();
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(testovi);
		InputStream inputStream=this.getClass().getResourceAsStream("/jasperreports/sviTestovi.jrxml");
		JasperReport report=JasperCompileManager.compileReport(inputStream);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("predmet", p.getNaziv());
		params.put("profesor", profa.getIme()+" "+profa.getPrezime()); 
		JasperPrint jasperPrint=JasperFillManager.fillReport(report,params,dataSource);
		inputStream.close();	
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment;filename=SviTestoviPredmeta.pdf");
		OutputStream out=response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);

	}
	@RequestMapping(value="pitanjaodgovori",method=RequestMethod.GET)
	public void showReport3(HttpServletResponse response,HttpServletRequest request)throws Exception{
		response.setContentType("text/html");
		
		List<Ajodgovor> lista=or.findByAjstudent((model.Ajstudent)request.getSession().getAttribute("osoba"));
		List<BinKlasa> listaa=new ArrayList<>();
		for(Ajodgovor a:lista) {
			listaa.add(new BinKlasa(a.getAjpitanja().getPitanje(),a.getOdgovor(),a.getAjpredmet().getNaziv()));
		}
		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(listaa);
		InputStream inputStream=this.getClass().getResourceAsStream("/jasperreports/pitanjaodgovori.jrxml");
		JasperReport report=JasperCompileManager.compileReport(inputStream);
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("student",((model.Ajstudent)request.getSession().getAttribute("osoba")).getIme());
		JasperPrint jasperPrint=JasperFillManager.fillReport(report,params,dataSource);
		inputStream.close();	
		
		response.setContentType("application/x-download");
		response.addHeader("Content-disposition", "attachment;filename=pitanjaodgovori.pdf");
		OutputStream out=response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint,out);

		
		
	}

}
