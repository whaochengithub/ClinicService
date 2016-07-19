
package edu.stevens.cs548.clinic.service.web.soap.provider;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import edu.stevens.cs548.clinic.service.web.soap.patient.PatientNotFoundExn;
import edu.stevens.cs548.clinic.service.web.soap.patient.SiteInfo;
import edu.stevens.cs548.clinic.service.web.soap.patient.SiteInfoResponse;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.stevens.cs548.clinic.service.web.soap.provider package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProviderByDbId_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "getProviderByDbId");
    private final static QName _GetProviderByNPI_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "getProviderByNPI");
    private final static QName _AddTreatmentForPatientResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "addTreatmentForPatientResponse");
    private final static QName _SiteInfoResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "siteInfoResponse");
    private final static QName _GetProviderByDbIdResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "getProviderByDbIdResponse");
    private final static QName _AddTreatmentForPatient_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "addTreatmentForPatient");
    private final static QName _GetProviderByNPIResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "getProviderByNPIResponse");
    private final static QName _PatientNotFoundExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "PatientNotFoundExn");
    private final static QName _SiteInfo_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "siteInfo");
    private final static QName _AddProviderResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "addProviderResponse");
    private final static QName _AddProvider_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "addProvider");
    private final static QName _ProviderNotFoundExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "ProviderNotFoundExn");
    private final static QName _ProviderServiceExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/provider", "ProviderServiceExn");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.stevens.cs548.clinic.service.web.soap.provider
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProviderNotFoundExn }
     * 
     */
    public ProviderNotFoundExn createProviderNotFoundExn() {
        return new ProviderNotFoundExn();
    }

    /**
     * Create an instance of {@link ProviderServiceExn }
     * 
     */
    public ProviderServiceExn createProviderServiceExn() {
        return new ProviderServiceExn();
    }

    /**
     * Create an instance of {@link AddProvider }
     * 
     */
    public AddProvider createAddProvider() {
        return new AddProvider();
    }

    /**
     * Create an instance of {@link AddProviderResponse }
     * 
     */
    public AddProviderResponse createAddProviderResponse() {
        return new AddProviderResponse();
    }

    /**
     * Create an instance of {@link AddTreatmentForPatient }
     * 
     */
    public AddTreatmentForPatient createAddTreatmentForPatient() {
        return new AddTreatmentForPatient();
    }

    /**
     * Create an instance of {@link GetProviderByNPIResponse }
     * 
     */
    public GetProviderByNPIResponse createGetProviderByNPIResponse() {
        return new GetProviderByNPIResponse();
    }

    /**
     * Create an instance of {@link GetProviderByDbIdResponse }
     * 
     */
    public GetProviderByDbIdResponse createGetProviderByDbIdResponse() {
        return new GetProviderByDbIdResponse();
    }

    /**
     * Create an instance of {@link AddTreatmentForPatientResponse }
     * 
     */
    public AddTreatmentForPatientResponse createAddTreatmentForPatientResponse() {
        return new AddTreatmentForPatientResponse();
    }

    /**
     * Create an instance of {@link GetProviderByNPI }
     * 
     */
    public GetProviderByNPI createGetProviderByNPI() {
        return new GetProviderByNPI();
    }

    /**
     * Create an instance of {@link GetProviderByDbId }
     * 
     */
    public GetProviderByDbId createGetProviderByDbId() {
        return new GetProviderByDbId();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderByDbId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "getProviderByDbId")
    public JAXBElement<GetProviderByDbId> createGetProviderByDbId(GetProviderByDbId value) {
        return new JAXBElement<GetProviderByDbId>(_GetProviderByDbId_QNAME, GetProviderByDbId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderByNPI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "getProviderByNPI")
    public JAXBElement<GetProviderByNPI> createGetProviderByNPI(GetProviderByNPI value) {
        return new JAXBElement<GetProviderByNPI>(_GetProviderByNPI_QNAME, GetProviderByNPI.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTreatmentForPatientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "addTreatmentForPatientResponse")
    public JAXBElement<AddTreatmentForPatientResponse> createAddTreatmentForPatientResponse(AddTreatmentForPatientResponse value) {
        return new JAXBElement<AddTreatmentForPatientResponse>(_AddTreatmentForPatientResponse_QNAME, AddTreatmentForPatientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "siteInfoResponse")
    public JAXBElement<SiteInfoResponse> createSiteInfoResponse(SiteInfoResponse value) {
        return new JAXBElement<SiteInfoResponse>(_SiteInfoResponse_QNAME, SiteInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderByDbIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "getProviderByDbIdResponse")
    public JAXBElement<GetProviderByDbIdResponse> createGetProviderByDbIdResponse(GetProviderByDbIdResponse value) {
        return new JAXBElement<GetProviderByDbIdResponse>(_GetProviderByDbIdResponse_QNAME, GetProviderByDbIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddTreatmentForPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "addTreatmentForPatient")
    public JAXBElement<AddTreatmentForPatient> createAddTreatmentForPatient(AddTreatmentForPatient value) {
        return new JAXBElement<AddTreatmentForPatient>(_AddTreatmentForPatient_QNAME, AddTreatmentForPatient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderByNPIResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "getProviderByNPIResponse")
    public JAXBElement<GetProviderByNPIResponse> createGetProviderByNPIResponse(GetProviderByNPIResponse value) {
        return new JAXBElement<GetProviderByNPIResponse>(_GetProviderByNPIResponse_QNAME, GetProviderByNPIResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientNotFoundExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "PatientNotFoundExn")
    public JAXBElement<PatientNotFoundExn> createPatientNotFoundExn(PatientNotFoundExn value) {
        return new JAXBElement<PatientNotFoundExn>(_PatientNotFoundExn_QNAME, PatientNotFoundExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "siteInfo")
    public JAXBElement<SiteInfo> createSiteInfo(SiteInfo value) {
        return new JAXBElement<SiteInfo>(_SiteInfo_QNAME, SiteInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "addProviderResponse")
    public JAXBElement<AddProviderResponse> createAddProviderResponse(AddProviderResponse value) {
        return new JAXBElement<AddProviderResponse>(_AddProviderResponse_QNAME, AddProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "addProvider")
    public JAXBElement<AddProvider> createAddProvider(AddProvider value) {
        return new JAXBElement<AddProvider>(_AddProvider_QNAME, AddProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderNotFoundExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "ProviderNotFoundExn")
    public JAXBElement<ProviderNotFoundExn> createProviderNotFoundExn(ProviderNotFoundExn value) {
        return new JAXBElement<ProviderNotFoundExn>(_ProviderNotFoundExn_QNAME, ProviderNotFoundExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderServiceExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/provider", name = "ProviderServiceExn")
    public JAXBElement<ProviderServiceExn> createProviderServiceExn(ProviderServiceExn value) {
        return new JAXBElement<ProviderServiceExn>(_ProviderServiceExn_QNAME, ProviderServiceExn.class, null, value);
    }

}
