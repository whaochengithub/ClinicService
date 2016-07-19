package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TreatmentExporter<T> implements ITreatmentExporter<T> {

	public T exportDrugTreatment(long tid, String diagnosis, String drug, float dosage) {
		DrugTreatment drugTreatment = new DrugTreatment();
		drugTreatment.setId(tid);
		drugTreatment.setDiagnosis(diagnosis);
		drugTreatment.setDrug(drug);
		drugTreatment.setDosage(dosage);
		@SuppressWarnings("unchecked")
		T trmt = (T)(drugTreatment);
		return trmt;
	}

	public T exportSurgery(long tid, String diagnosis, Date date) {
		Surgerytreatment surgerytreatment = new Surgerytreatment();
		surgerytreatment.setId(tid);
		surgerytreatment.setDiagnosis(diagnosis);
		surgerytreatment.setDate(date);
		@SuppressWarnings("unchecked")
		T trmt = (T)(surgerytreatment);
		return trmt;
	}

	public T exportRadiology(long tid, String diagnosis, List<RadDate> dates) {
		Radiologytreatment radiologytreatment = new Radiologytreatment();
		radiologytreatment.setId(tid);
		radiologytreatment.setDiagnosis(diagnosis);
		radiologytreatment.setDate(dates);
		@SuppressWarnings("unchecked")
		T trmt = (T)(radiologytreatment);
		return trmt;
	}

}
