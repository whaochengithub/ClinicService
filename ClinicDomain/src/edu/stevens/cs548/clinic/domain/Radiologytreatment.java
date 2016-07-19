package edu.stevens.cs548.clinic.domain;

import static javax.persistence.CascadeType.REMOVE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import static javax.persistence.CascadeType.PERSIST;

/**
 * Entity implementation class for Entity: Radiology
 *
 */
@Entity
@DiscriminatorValue("R")
public class Radiologytreatment extends Treatment implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = { REMOVE, PERSIST }, mappedBy = "radiologytreatment")
	@OrderBy
	private List<RadDate> dates;

	public List<RadDate> getDates() {
		return dates;
	}

	public void setDate(List<RadDate> dates) {
		this.dates = dates;
	}

	public <T> T export(ITreatmentExporter<T> visitor) {
		return visitor.exportRadiology (this.getId(), 
								   		this.getDiagnosis(),
								   		this.getDates());
	}
	
	@Transient
	private IRadDateDAO radDateDAO;
	
	public void setRadDateDAO (IRadDateDAO rdao){
		this.radDateDAO = rdao;
	}
	
	public void addRadDate (RadDate rd) {
		this.radDateDAO.addDate(rd);
		if (rd.getRadiology() != this) {
			rd.setRadiology(this);
		}
	}

	public Radiologytreatment() {
		super();
		this.setTreatmentType("R");
		dates = new ArrayList<RadDate>();
	}
   
}
