package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-11-17T14:33:27.166-0500")
@StaticMetamodel(RadDate.class)
public class RadDate_ {
	public static volatile SingularAttribute<RadDate, Long> id;
	public static volatile SingularAttribute<RadDate, Date> date;
	public static volatile SingularAttribute<RadDate, Radiologytreatment> radiologytreatment;
}
