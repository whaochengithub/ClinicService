package edu.stevens.cs548.clinic.service.web.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;

@WebService(
	name="IProviderWebPort",
	targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/provider")
/*
 * Endpoint interface for the provider Web service.
 */
public interface IProviderWebService {
	
	@WebMethod
	public long addProvider (
			@WebParam(name="provider-dto",
			          targetNamespace="http://cs548.stevens.edu/clinic/service/dto")
			ProviderDto dto) throws ProviderServiceExn;

	@WebMethod
	@WebResult(name="provider-dto",
			   targetNamespace="http://cs548.stevens.edu/clinic/service/dto")
	public ProviderDto getProviderByDbId(long id) throws ProviderServiceExn;
	
	@WebMethod
	@WebResult(name="provider-dto",
	   		   targetNamespace="http://cs548.stevens.edu/clinic/service/dto")
	public ProviderDto getProviderByNPI(long npi) throws ProviderServiceExn;

	@WebMethod(operationName="addTreatmentForPatient")
	public long addTreatmentForPat(
			@WebParam(name="treatment-dto",
	          targetNamespace="http://cs548.stevens.edu/clinic/service/dto") TreatmentDto dto) 
			throws PatientNotFoundExn, ProviderNotFoundExn, ProviderServiceExn;

	@WebMethod
	public String siteInfo();

}
