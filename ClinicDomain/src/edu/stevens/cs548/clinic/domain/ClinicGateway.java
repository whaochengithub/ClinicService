package edu.stevens.cs548.clinic.domain;

import javax.persistence.EntityManager;

public class ClinicGateway implements IClinicGateway {

	public IPatientFactory getPatientFactory() {
		return new PatientFactory();
	}
	
	public IProviderFactory getProviderFactory() {
		return new ProviderFactory();
	}

	public ITreatmentFactory getTreatmentFactory() {
		return new TreatmentFactory();
	}

	public IRadDateFactory getRadDateFactory() {
		return new RadDateFactory();
	}
	
	private EntityManager em;

	public IPatientDAO getPatientDAO() {
		return new PatientDAO(em);
	}
	
	public IProviderDAO getProviderDAO() {
		return new ProviderDAO(em);
	}

	public IRadDateDAO getRadDateDAO() {
		return new RadDateDAO(em);
	}

	public ClinicGateway(EntityManager em) {
		this.em = em;
	}

}
