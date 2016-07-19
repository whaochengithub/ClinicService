package edu.stevens.cs548.clinic.service.dto.util;

import java.util.Date;

import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;

public class PatientDtoFactory {
	
	ObjectFactory factory;
	
	public PatientDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public PatientDto createPatientDto () {
		return factory.createPatientDto();
	}
	
	public PatientDto createPatientDto (Patient p) {
		PatientDto d = factory.createPatientDto();
		/*
		 * TODO: Initialize the fields of the DTO.
		 */
		d.setId(p.getId());
		d.setPatientId(p.getPatientId());
		d.setName(p.getName());
		d.setDob(p.getBirthDate());
		return d;
	}
	
	public PatientDto createPatientDto (long pid, String name, Date dob, int age) {
		PatientDto d = factory.createPatientDto();
		/*
		 * TODO: Initialize the fields of the DTO.
		 */
		d.setPatientId(pid);
		d.setName(name);
		d.setDob(dob);
		d.setAge(age);
		return d;
	}

}
