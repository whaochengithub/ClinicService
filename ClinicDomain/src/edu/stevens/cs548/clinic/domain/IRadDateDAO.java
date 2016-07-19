package edu.stevens.cs548.clinic.domain;

public interface IRadDateDAO {
	
	public static class TreatmentExn extends Exception {
		private static final long serialVersionUID = 1L;
		public TreatmentExn (String msg) {
			super(msg);
		}
	}
	
	public void addDate (RadDate rd);

}
