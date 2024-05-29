package com.temxa.medas;

import java.io.Serializable;
import java.util.ArrayList;

public class FaturaBilgiDizi implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public ArrayList<FaturaBilgi> gonbilgi = new ArrayList<FaturaBilgi>();

	public ArrayList<FaturaBilgi> getGonbilgi() {
		return gonbilgi;
	}

	public void setGonbilgi(ArrayList<FaturaBilgi> gonbilgi) {
		this.gonbilgi = gonbilgi;
	}
	

}
