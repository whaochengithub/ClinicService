package edu.stevens.cs548.clinic.service.representations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.service.dto.RadDateType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.TreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.RadDates;
import edu.stevens.cs548.clinic.service.web.rest.data.dap.LinkType;

@XmlRootElement
public class TreatmentRepresentation extends TreatmentType {
	
	private ObjectFactory repFactory = new ObjectFactory();
	
	public LinkType getLinkPatient() {
		return this.getPatient();
	}
	
	public LinkType getLinkProvider() {
		return this.getProvider();
	}
	
	public void setLinkPatient(LinkType lt) {
		this.patient = lt;
	}
	
	public void setLinkProvider(LinkType lt) {
		this.provider = lt;
	}
	
	public static LinkType getTreatmentLink(long tid, UriInfo uriInfo) {
		UriBuilder ub = uriInfo.getBaseUriBuilder();
		ub.path("treatment");
		UriBuilder ubTreatment = ub.clone().path("{tid}");
		String treatmentURI = ubTreatment.build(Long.toString(tid)).toString();
	
		LinkType link = new LinkType();
		link.setUrl(treatmentURI);
		link.setRelation(Representation.RELATION_TREATMENT);
		link.setMediaType(Representation.MEDIA_TYPE);
		return link;
	}
	
	private TreatmentDtoFactory treatmentDtoFactory;
	
	public TreatmentRepresentation() {
		super();
		treatmentDtoFactory = new TreatmentDtoFactory();
	}
	
	public TreatmentRepresentation (TreatmentDto dto, UriInfo uriInfo) {
		this();
		this.id = getTreatmentLink(dto.getId(), uriInfo);
		this.patient =  PatientRepresentation.getPatientLink(dto.getPatient(), uriInfo);
		this.provider = ProviderRepresentation.getProviderLink(dto.getProvider(), uriInfo);		
		this.diagnosis = dto.getDiagnosis();
		
		if (dto.getDrugTreatment() != null) {
			this.drugTreatment = repFactory.createDrugTreatmentType();
			this.drugTreatment.setName(dto.getDrugTreatment().getName());
			this.drugTreatment.setDosage(dto.getDrugTreatment().getDosage());
		} else if (dto.getSurgery() != null) {
			this.surgery = repFactory.createSurgeryType();
			this.surgery.setDate(dto.getSurgery().getDate());
		} else if (dto.getRadiology() != null) {
			/*
			 * TODO Not sure about this part
			 */
			this.radiology = repFactory.createRadiologyType();
			RadDates rDate = repFactory.createRadDates();
			for (int i=0; i< dto.getRadiology().getDates().size(); i++){
				Date date = dto.getRadiology().getDates().get(i).getDate();
				rDate.setDate(date);
				this.radiology.getDates().add(rDate);
			}
		}
	}

	public TreatmentDto getTreatment() {
		TreatmentDto m = null;
		if (this.getDrugTreatment() != null) {
			m = treatmentDtoFactory.createDrugTreatmentDto(
					diagnosis, drugTreatment.getName(), drugTreatment.getDosage(), 
					Representation.getId(patient), Representation.getId(provider));
			
		} else if (this.getSurgery() != null) {
			m = treatmentDtoFactory.createSurgeryDto(
					diagnosis, surgery.getDate(), 
					Representation.getId(patient), Representation.getId(provider));		
		} else if (this.getRadiology() != null) {
			List<RadDateType> dates = new ArrayList<>();
			for(RadDates rd : radiology.getDates()){
				RadDateType rdt = new RadDateType();
				rdt.setDate(rd.getDate());
				dates.add(rdt);
			}	
			m = treatmentDtoFactory.createRadiologyDto(
					diagnosis, dates, 
					Representation.getId(patient), Representation.getId(provider));
		}
		return m;
	}
	
}
