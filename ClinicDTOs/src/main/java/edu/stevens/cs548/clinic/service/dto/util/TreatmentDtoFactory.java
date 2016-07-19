package edu.stevens.cs548.clinic.service.dto.util;

import java.util.Date;
import java.util.List;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.Radiologytreatment;
import edu.stevens.cs548.clinic.domain.Surgerytreatment;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.RadDateType;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public TreatmentDto createTreatmentDto() {
		return factory.createTreatmentDto();
	}

	public TreatmentDto createDrugTreatmentDto (
			String diagnosis, String drug, float dosage, 
			Long patientId, Long providerId) {
		TreatmentDto dto = factory.createTreatmentDto();
		
		DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
		drugInfo.setName(drug);
		drugInfo.setDosage(dosage);
		
		dto.setDiagnosis(diagnosis);
		dto.setPatient(patientId);
		dto.setProvider(providerId);
		dto.setDrugTreatment(drugInfo);
		return dto;
	}
	
	public TreatmentDto createSurgeryDto (
			String diagnosis, Date date, 
			Long patientId, Long providerId) {
		TreatmentDto dto = factory.createTreatmentDto();
		SurgeryType surInfo = factory.createSurgeryType();
		surInfo.setDate(date);
		dto.setDiagnosis(diagnosis);
		dto.setPatient(patientId);
		dto.setProvider(providerId);
		dto.setSurgery(surInfo);
		return dto;
	}

	public TreatmentDto createRadiologyDto (
			String diagnosis, List<RadDateType> dates,
			Long patientId, Long providerId) {
		TreatmentDto dto = factory.createTreatmentDto();
		dto.setDiagnosis(diagnosis);
		dto.setPatient(patientId);
		dto.setProvider(providerId);
		RadiologyType radInfo = factory.createRadiologyType();
		radInfo.setDates(dates);
//		RadDateType rdInfo;
//		List<RadDateType> rdList = new ArrayList<>();
//		for (int i=0; i< dates.size(); i++){	
//			rdInfo = factory.createRadDateType();
//			rdInfo.setDate(dates.get(i).getDate());
//			rdList.add(rdInfo);
//			radInfo.setDates(rdList);
//		}
		dto.setRadiology(radInfo);
		return dto;
	}
	
	public TreatmentDto createTreatmentDto (DrugTreatment dt) {
		// Initialize the DTO from the drug treatment.
		TreatmentDto t = factory.createTreatmentDto();
		t.setId(dt.getId());
		t.setDiagnosis(dt.getDiagnosis());
		t.setPatient(dt.getPatient().getPatientId());
		t.setProvider(dt.getProvider().getNpi());
		
		DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
		drugInfo.setName(dt.getDrug());
		drugInfo.setDosage(dt.getDosage());
		
		t.setDrugTreatment(drugInfo);
		
		return t;
	}
	
	public TreatmentDto createTreatmentDto (Surgerytreatment su) {
		// Initialize the DTO from the surgery.
		TreatmentDto t = factory.createTreatmentDto();
		t.setId(su.getId());
		t.setDiagnosis(su.getDiagnosis());
		t.setPatient(su.getPatient().getPatientId());
		t.setProvider(su.getProvider().getNpi());
		
		SurgeryType surInfo = factory.createSurgeryType();
		surInfo.setDate(su.getDate());
		
		t.setSurgery(surInfo);
		
		return t;
	}
	
	public TreatmentDto createTreatmentDto (Radiologytreatment rad) {
		// Initialize the DTO from the radiology.
		TreatmentDto t = factory.createTreatmentDto();
		t.setId(rad.getId());
		t.setDiagnosis(rad.getDiagnosis());
		t.setPatient(rad.getPatient().getPatientId());
		t.setProvider(rad.getProvider().getNpi());

		RadiologyType radInfo = factory.createRadiologyType();
		RadDateType rdInfo = factory.createRadDateType();
		for (int i=0; i<rad.getDates().size(); i++){
			rdInfo.setRadiology(rad.getId());
			rdInfo.setDate(rad.getDates().get(i).getDate());
			radInfo.getDates().add(rdInfo);
		}
		
		t.setRadiology(radInfo);
		
		return t;
	}

}
