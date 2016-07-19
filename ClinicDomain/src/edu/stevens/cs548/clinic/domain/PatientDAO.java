package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PatientDAO implements IPatientDAO {
	
	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	//2.Retrieve a patient aggregate from the system, given the primary key for the patient.
	public Patient getPatientByDbId(long id) throws PatientExn {
		Patient p = em.find(Patient.class, id);
		if (p == null) {
			throw new PatientExn("Patient not found: primary key = " + id);
		} else {
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}
	
	//3.Retrieve a patient aggregate from the system, given the patient identifier for the patient.
	public Patient getPatientByPatientId(long pid) throws PatientExn {
		TypedQuery<Patient> query = 
				em.createNamedQuery("SearchPatientByPatientID", Patient.class)
				.setParameter("pid", pid);
		List<Patient> patients = query.getResultList();
		if(patients.size() > 1)
			throw new PatientExn("Duplicate patient records: patient id = " +pid);
		else if (patients.size() < 1)
			throw new PatientExn("Patient not found: patient id = " +pid);
		else {
			Patient p = patients.get(0);
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}

	public List<Patient> getPatientByNameDob(String name, Date dob) {
		TypedQuery<Patient> query = 
				em.createNamedQuery("SearchPatientByNameDOB", Patient.class)
				.setParameter("name", name)
				.setParameter("dob", dob);
		List<Patient> patients = query.getResultList();
		for (Patient p : patients){
			p.setTreatmentDAO(this.treatmentDAO);
		}
		return patients;
	}

	//1.Add a new patient to the clinic.
	public long addPatient(Patient pat) throws PatientExn{
		long pid = pat.getPatientId();
		TypedQuery<Patient> query = 
				em.createNamedQuery("SearchPatientByPatientID", Patient.class).setParameter("pid", pid);
		List<Patient> patients = query.getResultList();
		if (patients.size() < 1) {
			em.persist(pat);
			pat.setTreatmentDAO(this.treatmentDAO);
			return pat.getId();
		}
		else {
			Patient pat2 = patients.get(0);
			throw new PatientExn("\nInsertion: Patient with patient id ("+ pid 
					+") already exists.\n** Name: "+ pat2.getName());
		}
	}

	public void deletePatient(Patient pat) throws PatientExn {
		em.remove(pat);
	}
	
	public void deletePatients() throws PatientExn {
		TypedQuery<Patient> query = em.createQuery("select p from Patient p", Patient.class);
		List<Patient> pats = query.getResultList();
		if (pats.size() < 1)
			throw new PatientExn("No patients to delete.");
		else
			for(Patient pat : pats){
				em.remove(pat);
			}
	}
	
	public PatientDAO (EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

}

