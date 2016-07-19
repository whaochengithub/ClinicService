package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TreatmentFactory implements ITreatmentFactory {

	public Treatment createDrugTreatment(String diagnosis, String drug, float dosage) {
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		treatment.setTreatmentType(TreatmentType.DRUG_TREATMENT.getTag());
		return treatment;
	}

	public Treatment createSurgery(String diagnosis, Date date) {
		Surgerytreatment treatment = new Surgerytreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(date);
		treatment.setTreatmentType(TreatmentType.SURGERY.getTag());
		return treatment;
	}
	
	public Treatment createRadiology(String diagnosis, List<RadDate> dates, IRadDateDAO radDateDAO) {
		Radiologytreatment treatment = new Radiologytreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDate(dates);
		treatment.setTreatmentType(TreatmentType.RADIOLOGY.getTag());
		treatment.setRadDateDAO(radDateDAO);
		return treatment;
	}

}
