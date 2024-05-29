package com.temxa.medas;

import java.io.Serializable;

public class Fatura implements Serializable {
	private static final long	serialVersionUID	= -5761457439563396253L;

	private int					id;
	private String				faturaTarih;
	private String				sonTarih;
	private String				tutar;

	public Fatura(String faturaTarih, String sonTarih, String tutar) {
		this.faturaTarih = faturaTarih;
		this.sonTarih = sonTarih;
		this.tutar = tutar;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFaturaTarih() {
		return faturaTarih;
	}

	public void setFaturaTarih(String faturaTarih) {
		this.faturaTarih = faturaTarih;
	}

	public String getSonTarih() {
		return sonTarih;
	}

	public void setSonTarih(String sonTarih) {
		this.sonTarih = sonTarih;
	}

	public String getTutar() {
		return tutar;
	}

	public void setTutar(String tutar) {
		this.tutar = tutar;
	}

}
