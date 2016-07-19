package edu.stevens.cs548.clinic.domain;

public class ProviderFactory implements IProviderFactory {

	public Provider createProvider(long npi, String name, SpecializationType spec) 
			throws IProviderDAO.ProviderExn {

		Provider pv = new Provider();
		pv.setNpi(npi);
		pv.setName(name);
		pv.setSpecialization(spec);
		return pv;
	}

}
