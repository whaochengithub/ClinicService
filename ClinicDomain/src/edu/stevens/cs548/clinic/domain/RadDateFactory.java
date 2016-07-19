package edu.stevens.cs548.clinic.domain;

import java.util.Date;

public class RadDateFactory implements IRadDateFactory {

	public RadDate createRadDate(Date date) {
		RadDate rd = new RadDate();
		rd.setDate(date);
		return rd;
	}

}
