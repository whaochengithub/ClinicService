package edu.stevens.cs548.clinic.domain;

public interface IClinicGateway {
	
	public IPatientFactory getPatientFactory();
	
	public IProviderFactory getProviderFactory();
	
	public ITreatmentFactory getTreatmentFactory();
	
	public IRadDateFactory getRadDateFactory();
	
	public IPatientDAO getPatientDAO();
	
	public IProviderDAO getProviderDAO();
	
	public IRadDateDAO getRadDateDAO();
	
}
