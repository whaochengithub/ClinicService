package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import javax.persistence.*;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity implementation class for Entity: Patient
 */

@Entity
@NamedQueries({
	@NamedQuery(
			name="SearchPatientByPatientID",
			query="select p from Patient p where p.pid = :pid"),
	@NamedQuery(
			name="SearchPatientByNameDOB",
			query="select p from Patient p where p.name= :name and p.birthDate = :dob")
})

@Table(name="PATIENT")

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	
	private long pid;
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientId() {
		return pid;
	}
	
	public void setPatientId(long pid) {
		this.pid = pid;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "patient")
	@OrderBy
	private List<Treatment> treatments;

	protected List<Treatment> getTreatments() {
		return treatments;
	}

	protected void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO (ITreatmentDAO tdao){
		this.treatmentDAO = tdao;
	}
	
	// 6.c. Define an insertion operation in the patient aggregate that takes the
	// treatment entity and inserts it into the database, using a treatment DAO.
	public long addTreatment (Treatment t) {
		// Persist treatment and set forward and backward links
		this.treatmentDAO.addTreatment(t);
		this.getTreatments().add(t);
		if (t.getPatient() != this) {
			t.setPatient(this);
		}
		if (t.getTreatmentType() == TreatmentType.RADIOLOGY.getTag()) {
			for(RadDate rd : ((Radiologytreatment)t).getDates() )
				((Radiologytreatment)t).addRadDate(rd);
		}
		return t.getId();
	}

	public long deleteTreatment (Treatment t) {
		this.treatmentDAO.deleteTreatment(t);
		this.getTreatments().remove(t);
		if (t.getPatient() != this) {
			t.setPatient(this);
		}
		return t.getId();
	}

	//7.a. Return a list of treatment identifiers for that patient’s treatments.
	public List<Long> getTreatmentIds() throws TreatmentExn {
		List<Long> tids = new ArrayList<Long>();
		if (treatments.size() < 1) { 
			throw new TreatmentExn("No treatment for this patient: PID = " + this.getPatientId());
		} else {
			for (Treatment treatment : treatments) {
				tids.add(treatment.getId());
			}
			return tids;
		}
	}
	
	//7.b. Allows a particular treatment, identified by a treatment identifier, 
	//     to be visited, taking a visitor object as one of its parameters.
	// Export a treatment without violated Aggregate pattern
	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		// Check that the exported treatment is a treatment for this patient.
		if (t.getPatient() != this) {
			throw new TreatmentExn("Inappropriate treatment access: patient = "+id+", treatment = "+tid);
		}
		return t.export(visitor);
	}
	
	public void deleteTreatment(long tid) throws TreatmentExn {
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		if(t.getPatient() == this){
			throw new TreatmentExn("Inappropriate treatment access: patient = "
								+ id +", treatment = "+ tid);
		}
		treatmentDAO.deleteTreatment(t);
	}
	
	public Patient() {
		super();
		treatments = new ArrayList<Treatment>();
	}
   
}
