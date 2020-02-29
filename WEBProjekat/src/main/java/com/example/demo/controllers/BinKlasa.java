package com.example.demo.controllers;

public class BinKlasa {
	private String pitanje;
	private String odgovor;
	private String imePredmeta;
	public BinKlasa(String a, String b,String c) {
		this.pitanje=a;
		this.odgovor=b;
		this.imePredmeta=c;
	}
	public String getImePredmeta() {
		return this.imePredmeta;
	}
	public void setImePredmeta(String aa) {
		this.imePredmeta=aa;
	}
	public String getPitanje() {
		return pitanje;
	}
	public void setPitanje(String pitanje) {
		this.pitanje = pitanje;
	}
	public String getOdgovor() {
		return odgovor;
	}
	public void setOdgovor(String odgovor) {
		this.odgovor = odgovor;
	}
}
