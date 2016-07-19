package edu.stevens.cs548.clinic.domain;

import javax.persistence.EntityManager;

public class RadDateDAO implements IRadDateDAO {
	
	private EntityManager em;
	
	public RadDateDAO (EntityManager em) {
		this.em = em;
	}
	
	public void addDate(RadDate rd) {
		em.persist(rd);
		//em.flush();
	}

}
