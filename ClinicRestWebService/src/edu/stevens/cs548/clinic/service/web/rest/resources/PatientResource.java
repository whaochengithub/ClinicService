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

import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.representations.PatientRepresentation;
import edu.stevens.cs548.clinic.service.representations.TreatmentRepresentation;

@Path("/patient")
@RequestScoped
public class PatientResource {
	
	final static Logger logger = Logger.getLogger(PatientResource.class.getCanonicalName());
	
	@Context
    private UriInfo uriInfo;
    
    private PatientDtoFactory patientDtoFactory;

    /**
     * Default constructor. 
     */
    public PatientResource() {
		patientDtoFactory = new PatientDtoFactory();
    }
    
    @Inject
    private IPatientServiceLocal patientService;
    
    @GET
    @Path("site")
    @Produces("text/plain")
    public String getSiteInfo() {
    	return patientService.siteInfo();
    }

	@POST
	@Consumes("application/xml")
    public Response addPatient( PatientRepresentation patientRep) {
    	try {
    		PatientDto dto = patientDtoFactory.createPatientDto();
    		dto.setPatientId(patientRep.getPatientId());
    		dto.setName(patientRep.getName());
    		dto.setDob(patientRep.getDob());
    		dto.setAge(patientRep.getAge());
    		long id = patientService.addPatient(dto);
    		logger.info(Long.toString(id));
    		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(id));
    		return Response.created(url).build();
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException();
    	}
    }
    
	/**
	 * Query methods for patient resources.
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public PatientRepresentation getPatientByDBId(@PathParam("id") String id) {
		try {
			long key = Long.parseLong(id);
			PatientDto patientDTO = patientService.getPatientByDbId(key);
			PatientRepresentation patientRep = new PatientRepresentation(patientDTO, uriInfo);
			return patientRep;
		} catch (PatientServiceExn e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
    
	@GET
	@Path("patientId")
	public PatientRepresentation getPatientByPatientId(@QueryParam("id") String patientId) {	
		try {
			long pid = Long.parseLong(patientId);
			PatientDto patientDto = patientService.getPatientByPatId(pid);
			PatientRepresentation patientRep = new PatientRepresentation(patientDto, uriInfo);
			return patientRep;
		} catch (PatientServiceExn e) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}
    
	@GET
    @Path("{id}/treatment/{tid}")
    @Produces("application/xml")
    public TreatmentRepresentation getPatientTreatment(@PathParam("id") String id, @PathParam("tid") String tid) {
    	try {
    		TreatmentDto treatment = patientService.getTreatment(Long.parseLong(id), Long.parseLong(tid)); 
    		TreatmentRepresentation treatmentRep = new TreatmentRepresentation(treatment, uriInfo);
    		return treatmentRep;
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    }

}