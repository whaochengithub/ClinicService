package edu.stevens.cs548.clinic.service.web.soap;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

@WebService(
		endpointInterface = "edu.stevens.cs548.clinic.service.web.soap.IProviderWebService", 
		targetNamespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", 
		serviceName = "ProviderWebService", portName = "ProviderWebPort")

@SOAPBinding(
		style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)

public class ProviderWebService implements IProviderWebService {
	
	@EJB(beanName="ProviderServiceBean")
	IProviderServiceLocal service;

	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		return service.addProvider(dto);
	}

	@Override
	public ProviderDto getProviderByDbId(long id) throws ProviderServiceExn {
		return service.getProviderByDbId(id);
	}

	@Override
	public ProviderDto getProviderByNPI(long npi) throws ProviderServiceExn {
		return service.getProviderByNPI(npi);
	}

	@Override
	public long addTreatmentForPat(TreatmentDto dto)
			throws PatientNotFoundExn, ProviderNotFoundExn, ProviderServiceExn {
		return service.addTreatmentForPat(dto);
	}

	@Override
	public String siteInfo() {
		return service.siteInfo();
	}

}
