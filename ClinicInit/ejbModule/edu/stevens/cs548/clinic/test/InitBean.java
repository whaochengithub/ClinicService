package edu.stevens.cs548.clinic.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.stevens.cs548.clinic.domain.ClinicGateway;
import edu.stevens.cs548.clinic.domain.IClinicGateway;
import edu.stevens.cs548.clinic.domain.IPatientFactory;
import edu.stevens.cs548.clinic.domain.IProviderFactory;
import edu.stevens.cs548.clinic.domain.IRadDateFactory;
import edu.stevens.cs548.clinic.domain.SpecializationType;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadDateType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.ClinicDomain;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.TreatmentNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

@Stateless
@LocalBean
@Startup
public class InitBean {

	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());
	
	private static void info(String msg) {
		logger.info(msg);
	}

	@Inject
	private IPatientServiceLocal patientService;
	@Inject
	private IProviderServiceLocal providerService;
	
	public InitBean() {	}
	
	@Inject @ClinicDomain EntityManager em;
	
	PatientDtoFactory patientDtoFactory = new PatientDtoFactory();
	ProviderDtoFactory providerDtoFactory = new ProviderDtoFactory();
	
	@PostConstruct
	private void init() {
        
		info("Your name here: Peiying Deng");
		
		IClinicGateway clinicGateway = new ClinicGateway(em);

		IPatientFactory patientFactory = clinicGateway.getPatientFactory();		
		IProviderFactory providerFactory = clinicGateway.getProviderFactory();	
		IRadDateFactory radDateFactory = clinicGateway.getRadDateFactory();
		
		TreatmentDtoFactory treatmentDtoFactory = new TreatmentDtoFactory();
			
		/*
		 * Clear the database and populate with fresh data.
		*/ 
		try {
			providerService.deleteProviders();
			info("Delete all providers and corresponding treatments from database.");
			patientService.deletePatients();
			info("Delete all patients from database.");
			info("Initializing database done!");
		} catch (ProviderServiceExn e) { info( e.toString() ); 
		} catch (PatientServiceExn e) { info( e.toString() ); } 
		
//		createPatient(patientFactory);
//		
//		retrievePatientbyPK(301);
//		retrievePatientbyPID(71500001);
//		
//		createProvider(providerFactory);
//		
//		retrieveProviderbyPK(304);
//		retrieveProviderbyNPI(200002);
//		
//		addTreatmentByPatient(treatmentDtoFactory, radDateFactory);
//
//		retrieveATreatmentByPatient (301, 307);
//		retrieveATreatmentByPatient (302, 262);
//		retrieveATreatmentByPatient (301, 262);
//		retrieveATreatmentByPatient (301, 1234);
//		retrieveATreatmentByPatient (301, 308);
		
	}

	@PreDestroy
	private void destroy() {
		em.close();
	}
	
	/* Date 2 String, or vice versa. */
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date d = new Date();
	
	/* Test data for patients. */
	long[] PIDs = { 71500001, 71500001, 71500002, 71500001 };
	String[] patientNames = { "Tony Stark", "Tony Stark", "Steve Rogers", "John Doe" };
	String[] dobs = { "1969-05-29", "1969-05-29", "1988-07-04", "1984-09-04" };
	int[] ages = { 45, 46, 27, 31 };
	
	/* Test data for providers. */
	long[] NPIs = { 200001, 200002, 200003, 200003 };
	String[] providerNames = { "Mount Sinai H.", "SHIELD Hospital", "NY Oncology Hematology", "xxxx" };
	String[] specs = { "RADIOLOGY", "surgery", "Oncology", "SURGERY" };
	
	/* Test data for treatments. */
	String[] radDates = { "2012-05-01", "2012-06-07", "2012-08-09"};	

	// TODO Adding a patient to a clinic.
	private void createPatient(IPatientFactory patFactory) {
		for(int i=0; i<PIDs.length; i++){
			info("——Create a new patient into database.——");
			
			try {
				d = formatter.parse(dobs[i]);
				
				PatientDto patientDto = patientDtoFactory.createPatientDto(PIDs[i], patientNames[i], d, ages[i]);
				long id = patientService.addPatient(patientDto);
				
				info("Added \""+patientDto.getName()+"\" with patient id "
						+ patientDto.getPatientId() +", Auto generated ID "+ id);
				
			} catch (PatientServiceExn e) {
				info("\\\\* Failed to add a patient to clinic \n" + e.toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	// TODO Obtaining a single patient DTO, given a patient key (database primary key). 
	private void retrievePatientbyPK (long id) {

		info("Retrieve the patient for a primary key.");
		
		try{
			PatientDto patientDto = patientService.getPatientByDbId(id);
			info("getPatientByDbid(" + patientDto.getId() + "): Successfully! PID(" 
					+ patientDto.getPatientId() + "), name(" + patientDto.getName() + ").");
		} catch (PatientServiceExn e) {
			info("*There is no patient for the specified patient key " + id + "!* " + e.toString() );
		}
		
	}
	
	// TODO Obtaining a single patient DTO, given a patient identifier.
	private void retrievePatientbyPID (long pid) {

		info("Return a patient given a patient identifier.");
		
		try{
			PatientDto patientDto = patientService.getPatientByPatId(pid);
			info("getPatientByPatientId(" + patientDto.getPatientId() + "): Successfully! Patient key(" 
					+ patientDto.getId() + "), name(" + patientDto.getName() + ").");
		} catch (PatientServiceExn e) {
			info("*There is no patient for the specified patient identifier " + pid + "!* " + e.toString() );
		}
		
	}
	/*
	// TODO Adding a provider to a clinic.
	private void createProvider(IProviderFactory providerFactory) {

		try {
			for (int i = 0; i < NPIs.length; i++) {
				info("——Create a new provider into database.——");

				ProviderDto providerDto = providerDtoFactory.createProviderDto(
						NPIs[i], providerNames[i], SpecializationType.valueOf(specs[i].toUpperCase()) );

				long id = providerService.addProvider(providerDto);

				info("Added \""+providerDto.getName()+"\" with NPI "
						+ providerDto.getNpi() +", Auto generated ID "+ id);
			}
		} catch (ProviderServiceExn e) {
			info("\\\\* Failed to add a provider \n" + e.toString());
		}
	}
	*/
	// TODO Obtaining a single provider DTO, given a provider key.
	private void retrieveProviderbyPK (long id) {

		info("Return the provider for a primary key.");
		
		try{
			ProviderDto providerDto = providerService.getProviderByDbId(id);
			info("getProviderByDbid(" + id + "): Successfully! name(" + providerDto.getName() + ").");
		} catch (ProviderServiceExn e) {
			info("*There is no provider for the specified key " + id + "!* " + e.toString() );
		}
		
	}
	
	// TODO Obtaining a single provider DTO, given a national provider identifier (NPI).
	private void retrieveProviderbyNPI (long id) {

		info("Return a provider given a NPI.");
		
		try{
			ProviderDto providerDto = providerService.getProviderByNPI(id);
			info("getProviderByNPI(" + id + "): Successfully! DB Id(" + providerDto.getId() + "), name(" + providerDto.getName() + ").");
		} catch (ProviderServiceExn e) {
			info("*There is no provider for the specified NPI " + id + "!* " + e.toString() );
		}
		
	}

	// TODO Add a treatment for a patient.
	private void addTreatmentByPatient(
			TreatmentDtoFactory trmtDtoFactory, IRadDateFactory radDateFactory) {
		info("——Add a treatment for a patient.——");

		try {
			PatientDto patDto1 = patientService.getPatientByPatId(71500001);
			PatientDto patDto2 = patientService.getPatientByPatId(71500002);
			
			ProviderDto prvDto1 = providerService.getProviderByNPI(200001);
			ProviderDto prvDto2 = providerService.getProviderByNPI(200002);
			ProviderDto prvDto3 = providerService.getProviderByNPI(200003);
			
			Date su_date = formatter.parse("2014-10-17"); //surgery date

			List<RadDateType> rDates = new ArrayList<>();
			for (int i=0; i<radDates.length ;i++){	// radiology dates list
				d = formatter.parse(radDates[i]);
				RadDateType rdt = new RadDateType();
				rdt.setDate(d);
				rDates.add(rdt);
			}

			TreatmentDto tDto1 = trmtDtoFactory.
					createDrugTreatmentDto("Headache", "Painkiller", (float)3.5, patDto1.getId(), prvDto1.getId());
			TreatmentDto tDto2 = trmtDtoFactory.
					createSurgeryDto("BoneTwist", su_date, patDto1.getId(), prvDto2.getId());
			TreatmentDto tDto3 = trmtDtoFactory.
					createRadiologyDto("Cancer", rDates, patDto1.getId(), prvDto3.getId());
			TreatmentDto tDto4 = trmtDtoFactory.
					createDrugTreatmentDto("Cold", "Painkiller", (float)1.5, patDto2.getId(), prvDto1.getId());

			long id1 = providerService.addTreatmentForPat(tDto1);
			info("Add Treatment (Treatment Id: " + id1 + ") Successfully Done!");
			long id2 = providerService.addTreatmentForPat(tDto2);
			info("Add Treatment (Treatment Id: " + id2 + ") Successfully Done!");
			long id3 = providerService.addTreatmentForPat(tDto3);
			info("Add Treatment (Treatment Id: " + id3 + ") Successfully Done!");
			long id4 = providerService.addTreatmentForPat(tDto4);
			info("Add Treatment (Treatment Id: " + id4 + ") Successfully Done!");		
			
		} catch (ParseException e) {
			info(e.toString());	
		} catch (PatientNotFoundExn e) {
			info(e.toString());
		} catch (ProviderNotFoundExn e) {
			info(e.toString());
		} catch (PatientServiceExn e) {
			info("——Failed to add the treatment for the patient——");
			info("Couldn't find this patient." + e.toString());
		} catch (ProviderServiceExn e) {
			info("——Failed to add the treatment for the patient——");
			info("Couldn't find this provider." + e.toString());
		}
	}
	
	// TODO Return the DTO for a treatment, given a patient key and the key for the treatment.
	private void retrieveATreatmentByPatient (long id, long tid) {
		
		info("——Search Treatment "+ tid +" for Patient "+ id +" —— ");
		
		try {
			TreatmentDto treatmentDto = patientService.getTreatment(id, tid);
			
			info("Information of treatment "+ tid +" for patient " + id +"【");
			info("Provider key: "+ treatmentDto.getProvider());
			info("Diagnosis: "+ treatmentDto.getDiagnosis());
			if (treatmentDto.getDrugTreatment() != null) {
				info("Type [ DrugTreatment ]");
				info("Drug Name: " + treatmentDto.getDrugTreatment().getName() );
				info("Dosage: " + treatmentDto.getDrugTreatment().getDosage() );
			} else if (treatmentDto.getSurgery() != null) {
				info("Type [ Surgery ]");
				info("Surgery Date: " + formatter.format(treatmentDto.getSurgery().getDate()) );
			} else {
				info("Type [ Radiology ]");
				info("Dates: ");
				for(RadDateType rd : treatmentDto.getRadiology().getDates() ){
					info( formatter.format(rd.getDate()) );
				}
			}
			info("】");
		} catch (PatientNotFoundExn e) {
			info(e.toString() );
		} catch (TreatmentNotFoundExn e) {
			info(e.toString() );
		} catch (PatientServiceExn e) {
			info(e.toString() );
		}		
	}
	
}
