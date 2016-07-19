package edu.stevens.cs548.clinic.service.ejb;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IPatientFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.RadDate;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.domain.TreatmentDAO;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.RadDateType;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name="PatientServiceBean")
public class PatientService implements IPatientServiceLocal, IPatientServiceRemote {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientService.class.getCanonicalName());

	private IPatientFactory patientFactory;

	private IPatientDAO patientDAO;
	private ITreatmentDAO treatmentDAO;

	/**
	 * Default constructor.
	 */
	public PatientService() {
		patientFactory = new PatientFactory();
	}
	
	// TODO use dependency injection and EJB lifecycle methods to initialize DAOs
	@PersistenceContext(unitName="ClinicDomain") 
	private EntityManager em;
	
	@PostConstruct
	private void initialize() {
		patientDAO = new PatientDAO(em);
		treatmentDAO = new TreatmentDAO(em);
	}
	
	private PatientDto PatientPDOtoDTO (Patient patient) throws PatientServiceExn {
		
		ObjectFactory factory = new ObjectFactory();		
		PatientDto dto = factory.createPatientDto();
		
		dto.setId(patient.getId());
		dto.setPatientId(patient.getPatientId());
		dto.setName(patient.getName());
		dto.setDob(patient.getBirthDate());
//		List<Long> tids;
//		try {
//			tids = patient.getTreatmentIds();
//			for (int i = 0; i < tids.size(); i++) {
//				dto.getTreatments().add(tids.get(i));
//			}
//		} catch (TreatmentExn e) {
//			throw new PatientServiceExn(e.toString());
//		}
		return dto;
	}

	/**
	 * @see IPatientService#addPatient(String, Date, long)
	 */
	@Override
	public long addPatient(PatientDto dto) throws PatientServiceExn {
		// Use factory to create patient entity, and persist with DAO
		try {
			Patient patient = patientFactory.createPatient(dto.getPatientId(), dto.getName(), dto.getDob(), dto.getAge());
			patientDAO.addPatient(patient);
			return patient.getId();
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatient(long)
	 */
	@Override
	public PatientDto getPatientByDbId(long id) throws PatientServiceExn {
		// TODO use DAO to get patient by database key
		try{
			Patient patient = patientDAO.getPatientByDbId(id);
			return PatientPDOtoDTO(patient);
		} catch (PatientExn e) {
			throw new PatientServiceExn("Patient key " + id + "not found!");
		}
	}

	/**
	 * @see IPatientService#getPatientByPatId(long)
	 */
	@Override
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn {
		// TODO use DAO to get patient by patient id
		try{
			Patient patient = patientDAO.getPatientByPatientId(pid);
			return PatientPDOtoDTO(patient);
		} catch (PatientExn e) {
			throw new PatientServiceExn("Patient id " + pid + "not found!");
		}
	}

	public class TreatmentExporter implements ITreatmentExporter<TreatmentDto> {
		
		private ObjectFactory factory = new ObjectFactory();
		TreatmentDto dto = factory.createTreatmentDto();
		
		@Override
		public TreatmentDto exportDrugTreatment(
				long tid, String diagnosis, String drug, float dosage) {
			dto.setId(tid);
			dto.setDiagnosis(diagnosis);
			DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setName(drug);
			dto.setDrugTreatment(drugInfo);
			return dto;
		}

		@Override
		public TreatmentDto exportSurgery(long tid, String diagnosis, Date date) {
			dto.setId(tid);
			dto.setDiagnosis(diagnosis);
			SurgeryType surgeryInfo = factory.createSurgeryType();
			surgeryInfo.setDate(date);
			dto.setSurgery(surgeryInfo);
			return dto;
		}

		@Override
		public TreatmentDto exportRadiology(long tid, String diagnosis, List<RadDate> dates) {
			dto.setId(tid);
			dto.setDiagnosis(diagnosis);
			RadiologyType radiologyInfo = factory.createRadiologyType();
			RadDateType radDateInfo;
			for (int i = 0; i < dates.size(); i++) {
				radDateInfo = factory.createRadDateType();
				radDateInfo.setDate(dates.get(i).getDate());
				radDateInfo.setRadiology(tid);
				radiologyInfo.getDates().add(radDateInfo);
			}
			dto.setRadiology(radiologyInfo);
			return dto;
		}
		
	}
	
	/**
	 * @see IPatientService#getTreatment(long, long)
	 */
	@Override
	public TreatmentDto getTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// Export treatment DTO given a patient key and a treatment key
		try {
			Patient patient = patientDAO.getPatientByDbId(id);
			Treatment t = treatmentDAO.getTreatmentByDbId(tid);
			// Check that the treatment is in fact for the specified patient.
			if (t.getPatient().getId() != id) {
				throw new PatientServiceExn("The treatment("+ tid 
						+") is not for the specified patient(" + id +").");
			}
			TreatmentExporter visitor = new TreatmentExporter();
			TreatmentDto tdto = patient.exportTreatment(tid, visitor);
			tdto.setPatient(id);
			tdto.setProvider(t.getProvider().getId());
			return tdto;
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (TreatmentExn e) {
			throw new TreatmentNotFoundExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#deletePatient(String, long)
	@Override
	public void deletePatient(String name, long id) throws PatientServiceExn {
		// TODO Auto-generated method stub
		try{
			Patient patient = patientDAO.getPatientByDbId(id);
			if (!name.equals(patient.getName())) {
				throw new PatientServiceExn(
						"Tried to delete wrong patient: name = " + name
						+ " , id = " + id);
			} else {
				patientDAO.deletePatient(patient);
			}
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}
	 */

	@Override
	public void deletePatients() throws PatientServiceExn {
		try{
			patientDAO.deletePatients();
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
		
	}

	@Resource(name="SiteInfo")
	private String siteInformation;

	@Override
	public String siteInfo() {
		return siteInformation;
	}

}
