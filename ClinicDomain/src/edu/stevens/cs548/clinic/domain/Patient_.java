package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-10-16T15:55:55.657-0400")
@StaticMetamodel(Patient.class)
public class Patient_ {
	public static volatile SingularAttribute<Patient, Long> id;
	public static volatile SingularAttribute<Patient, Long> pid;
	public static volatile SingularAttribute<Patient, String> name;
	public static volatile SingularAttribute<Patient, Date> birthDate;
	public static volatile ListAttribute<Patient, Treatment> treatments;
}
