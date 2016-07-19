package edu.stevens.cs548.clinic.domain;

import javax.persistence.EntityManager;

public class ClinicGatewayFactory {

	private static EntityManager em;

	public static ClinicGateway createClinicGateway(){
		return new ClinicGateway(em);
	}
}
