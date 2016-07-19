package edu.stevens.cs548.clinic.service.web.rest.resources;

import java.net.URI;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;
import edu.stevens.cs548.clinic.service.representations.ProviderRepresentation;
import edu.stevens.cs548.clinic.service.representations.TreatmentRepresentation;

@Path("/provider")
@RequestScoped
public class ProviderResource {
	
	final static Logger logger = Logger.getLogger(ProviderResource.class.getCanonicalName());
	
	@Context
    private UriInfo uriInfo;
    
    private ProviderDtoFactory providerDtoFactory;

    /**
     * Default constructor. 
     */
    public ProviderResource() {
		providerDtoFactory = new ProviderDtoFactory();
    }
    
    @Inject
    private IProviderServiceLocal providerService;
    
    @GET
    @Path("site")
    @Produces("text/plain")
    public String getSiteInfo() {
    	return providerService.siteInfo();
    }

	@POST
	@Consumes("application/xml")
    public Response addProvider(ProviderRepresentation providerRep) {
    	try {
    		ProviderDto dto = providerDtoFactory.createProviderDto();
    		dto.setNpi(providerRep.getNpi());
    		dto.setName(providerRep.getName());
    		//dto.setSpecialization(edu.stevens.cs548.clinic.service.dto.SpecializationType.valueOf(providerRep.getSpecialization().value().toUpperCase()));
    		long id = providerService.addProvider(dto);
    		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(id));
    		return Response.created(url).build();
    	} catch (ProviderServiceExn e) {
    		throw new WebApplicationException();
    	}
    }
    
	/**
	 * Query methods for provider resources.
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public ProviderRepresentation getProviderByDBId(@PathParam("id") String id) {
		try {
			long key = Long.parseLong(id);
			ProviderDto providerDTO = providerService.getProviderByDbId(key);
			ProviderRepresentation providerRep = new ProviderRepresentation(providerDTO, uriInfo);
			return providerRep;
		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
    
	@GET
	@Path("npi")
	public ProviderRepresentation getProviderByNPI(@QueryParam("id") String id) {	
		try {
			long npi = Long.parseLong(id);
			ProviderDto providerDto = providerService.getProviderByNPI(npi);
			ProviderRepresentation providerRep = new ProviderRepresentation(providerDto, uriInfo);
			return providerRep;
		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
       
    @POST
    @Path("addtrmt")
	@Consumes("application/xml")
    public Response addTreatmentForPat(TreatmentRepresentation treatmentRep){
		try {
			treatmentRep.setLinkPatient(treatmentRep.getPatient());
			treatmentRep.setLinkProvider(treatmentRep.getProvider());
			//long ptid = Long.parseLong(patientDbId);
    		//long pvid = Long.parseLong(providerDbId);
	    	TreatmentDto tdto = treatmentRep.getTreatment();
	    	logger.info(Long.toString(tdto.getPatient()));
	    	logger.info(Long.toString(tdto.getProvider()));
	    	//tdto.setPatient(801L);
	    	//tdto.setProvider(pvid);
			long tid = providerService.addTreatmentForPat(tdto);
			UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}").path("treatment");
			URI url = ub.build(Long.toString(tid));
			return Response.created(url).build();
		} catch (PatientNotFoundExn | ProviderServiceExn e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
    }
    
}