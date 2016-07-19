package edu.stevens.cs548.clinic.domain;

import javax.persistence.EntityManager;

public class TreatmentDAO implements ITreatmentDAO {

	private EntityManager em;
	
	public TreatmentDAO (EntityManager em) {
		this.em = em;
	}

	public Treatment getTreatmentByDbId(long id) throws TreatmentExn {
		Treatment t = em.find(Treatment.class, id);
		if (t == null) {
			throw new TreatmentExn("Missing treatment: id = " + id);
		} else {
			return t;
		}
	}

	public long addTreatment(Treatment t) {
		em.persist(t);
		em.flush();
		return t.getId();
	}

	public void deleteTreatment(Treatment t) {
		em.remove(t);
	}
	
}
