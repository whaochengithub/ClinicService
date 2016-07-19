package edu.stevens.cs548.clinic.service.ejb;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.IProviderFactory;
import edu.stevens.cs548.clinic.domain.IRadDateDAO;
import edu.stevens.cs548.clinic.domain.IRadDateFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentFactory;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.RadDate;
import edu.stevens.cs548.clinic.domain.RadDateDAO;
import edu.stevens.cs548.clinic.domain.RadDateFactory;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
//import edu.stevens.cs548.clinic.domain.SpecializationType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadDateType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
//import edu.stevens.cs548.clinic.service.dto.SpecializationType;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class ProviderService
 */
@Stateless(name="ProviderServiceBean")
public class ProviderService implements IProviderServiceLocal, IProviderServiceRemote {

	private IProviderFactory providerFactory;
	private ITreatmentFactory treatmentFactory;
	
	private IProviderDAO providerDAO;	
	private IPatientDAO patientDAO;
	
	private IRadDateFactory radDateFactory;
	private IRadDateDAO radDateDAO;
    
	/**
     * Default constructor. 
     */
    public ProviderService() {
    	providerFactory = new ProviderFactory();
    	treatmentFactory = new TreatmentFactory();
    	radDateFactory = new RadDateFactory();
    }
    
    // TODO use dependency injection and EJB lifecycle methods to initialize DAOs
    @PersistenceContext(unitName="ClinicDomain")
 	private EntityManager em;
 	
 	@PostConstruct
	private void initialize() {
 		patientDAO = new PatientDAO(em);
 		providerDAO = new ProviderDAO(em);
 		radDateDAO = new RadDateDAO(em);
	}
 	
 	private ProviderDto ProviderPDOtoDTO (Provider provider) throws ProviderServiceExn{
		ObjectFactory factory = new ObjectFactory();
		ProviderDto dto = factory.createProviderDto();
		
		dto.setId(provider.getId());
		dto.setNpi(provider.getNpi());
		dto.setName(provider.getName());
	
		dto.setSpecialization(provider.getSpecialization());
//		List<Long> tids;
//		try {
//			tids = provider.getTreatmentIds();
//			for (int i = 0; i < tids.size(); i++) {
//				dto.getTreatments().add(tids.get(i));
//			}
//		} catch (TreatmentExn e) {
//			throw new ProviderServiceExn(e.toString());
//		}
		return dto;
	}

	/**
     * @throws ProviderServiceExn 
	 * @see IProviderService#addProvider(ProviderDto)
     */
    public long addProvider(ProviderDto dto) throws ProviderServiceExn {
    	// Use factory to create provider entity, and persist with DAO
		try { 
			Provider provider = providerFactory.createProvider(dto.getNpi(), dto.getName(), dto.getSpecialization());
			providerDAO.addProvider(provider);
			return provider.getId();
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
    }

	/**
     * @see IProviderService#getProviderByDbId(long)
     */
    public ProviderDto getProviderByDbId(long id) throws ProviderServiceExn {
    	// TODO use DAO to get provider by database key
		try{
			Provider provider = providerDAO.getProviderByDbId(id);
			return ProviderPDOtoDTO(provider);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
    }

	/**
     * @see IProviderService#getProviderByPatId(long)
     */
    public ProviderDto getProviderByNPI(long npi) throws ProviderServiceExn {
    	// TODO use DAO to get provider by NPI
		try{
			Provider provider = providerDAO.getProviderByNPI(npi);
			return ProviderPDOtoDTO(provider);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
    }
    
    /*
    private TreatmentDto TreatmentPDOtoDTO (Treatment t) {		
		ObjectFactory factory = new ObjectFactory();
		TreatmentDto dto = factory.createTreatmentDto();
		
		dto.setId(t.getId());
		dto.setDiagnosis(t.getDiagnosis());
		dto.setPatient(t.getPatient().getPatientId());
		dto.setProvider(t.getProvider().getNpi());
    	switch(t.getTreatmentType()){
	    	case "DT":
	    		dto.setDrugTreatment(new DrugTreatmentType());
	    	case "SU":
	    		dto.setSurgery(new SurgeryType());
	    	case "RA":
	    		dto.setRadiology(new RadiologyType());
    	}
		return dto;
	}
	*/	

    private Treatment TreatmentDTOToPDO (TreatmentDto dto) {		

		Treatment trmt = null;

		if (dto.getDrugTreatment() != null){
			trmt = treatmentFactory.createDrugTreatment(
					dto.getDiagnosis(), dto.getDrugTreatment().getName(), dto.getDrugTreatment().getDosage());
		} else if (dto.getSurgery() != null) {
			trmt = treatmentFactory.createSurgery(
					dto.getDiagnosis(), dto.getSurgery().getDate());
		} else {
			List<RadDate> radDates = new ArrayList<>();
			List<RadDateType> radDateTypes = dto.getRadiology().getDates();
			for (int i = 0; i < radDateTypes.size(); i++) {
				RadDate radDate = radDateFactory.createRadDate(radDateTypes.get(i).getDate());
				radDates.add(radDate);
			}
			trmt = treatmentFactory.createRadiology(
					dto.getDiagnosis(), radDates, radDateDAO);
		}
		return trmt;
	}
    
    /**
     * @see IProviderService#addTreatmentByPat(TreatmentDto)
     */
    public long addTreatmentForPat(TreatmentDto dto) 
    		throws PatientNotFoundExn, ProviderNotFoundExn, ProviderServiceExn {
    	// Use factory to create treatment entity, and persist with DAO
    	// specifies the patient (by patient key) and the responsible provider (by provider key).
		try {
			Patient pat = patientDAO.getPatientByDbId(dto.getPatient());
			Provider pro = providerDAO.getProviderByDbId(dto.getProvider());
			return pro.addTreatment(TreatmentDTOToPDO (dto), pat);
		} catch (PatientExn e){
			throw new PatientNotFoundExn("Couldn't find this patient." + e.toString());
		} catch (ProviderExn e) {
			throw new ProviderNotFoundExn("Couldn't find this provider." + e.toString());
		}

    }
    
    @Override
    public void deleteProviders() throws ProviderServiceExn {
		try{
			providerDAO.deleteProviders();
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
	}	

    @Resource(name="SiteInfo")
 	private String siteInformation;

 	@Override
 	public String siteInfo() {
 		return siteInformation;
 	}

}
