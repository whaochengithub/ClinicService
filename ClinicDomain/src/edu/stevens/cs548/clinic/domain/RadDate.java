package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: RadDate
 *
 */

@Entity
public class RadDate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	@Temporal(TemporalType.DATE)
	private Date date;	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@ManyToOne
	@JoinColumn(name = "radiology_fk", referencedColumnName = "id") 
	private Radiologytreatment radiologytreatment;
	
	public Radiologytreatment getRadiology() {
		return radiologytreatment;
	}
	
	public void setRadiology(Radiologytreatment radiologytreatment) {
		this.radiologytreatment = radiologytreatment;
		if (!radiologytreatment.getDates().contains(this))
			radiologytreatment.addRadDate(this);
	}

	public RadDate() {
		super();
	}
   
}
