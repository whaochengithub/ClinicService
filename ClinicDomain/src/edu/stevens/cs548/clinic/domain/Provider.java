package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

/**
 * Entity implementation class for Entity: Provider
 */

@Entity
@NamedQueries({
	@NamedQuery(
			name="SearchProviderByNPI",
			query="select pv from Provider pv where pv.npi = :npi"),
	@NamedQuery(
			name="FindAllProviders",
			query="select pv from Provider pv")
})

@Table(name="PROVIDER")

public class Provider implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private long npi;
	private String name;
	@Enumerated(EnumType.STRING)
	private SpecializationType specialization;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNpi() {
		return npi;
	}

	public void setNpi(long npi) {
		this.npi = npi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public SpecializationType getSpecialization() {
		return specialization;
	}

	public void setSpecialization(SpecializationType specialization) {
		this.specialization = specialization;
	}

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "provider")
	private List<Treatment> treatments;
	 
	protected List<Treatment> getTreatments() {
		return treatments;
	}
	 
	protected void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	/*
	 * Addition and deletion of treatments should be done in the provider aggregate.
	 */
	 
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO (ITreatmentDAO tdao){
		this.treatmentDAO = tdao;
	}

	// 6.b. Define an insertion operation for inserting a treatment into the database.
	// This operation should take a treatment entity and an associated patient entity.
	public long addTreatment (Treatment t, Patient pat) {
		this.getTreatments().add(t);
		pat.addTreatment(t);
		if(t.getProvider()!= this)
			t.setProvider(this);
		return t.getId();
	}
	
	//8. a. Return a list of all treatments (treatment identifiers) associated with that provider.
	public List<Long> getTreatmentIds() throws TreatmentExn {
		List<Long> tids = new ArrayList<Long>();
		if (treatments.size() < 1) { 
			throw new TreatmentExn("No treatment under this provider: NPI = " + this.getNpi());
		} else {
			for (Treatment treatment : treatments) {
				tids.add(treatment.getId());
			}
			return tids;
		}	
	}
	
	//8. b.Visit a particular treatment specified by a treatment identifier (primary key).
	// Export a treatment without violated Aggregate pattern
	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		Treatment t = treatmentDAO.getTreatmentByDbId(tid);
		// Check that the exported treatment is a treatment for this patient.
		if (t.getProvider() != this) {
			throw new TreatmentExn("Inappropriate treatment access: provider = "+id+", treatment = "+tid);
		}
		return t.export(visitor);
	}

	public Provider() {
		super();
		treatments = new ArrayList<Treatment>();
	}
   
}
