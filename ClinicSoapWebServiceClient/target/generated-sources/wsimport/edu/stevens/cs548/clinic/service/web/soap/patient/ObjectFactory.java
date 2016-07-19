
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.stevens.cs548.clinic.service.web.soap.patient package. 
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

    private final static QName _PatientNotFoundExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/patient", "PatientNotFoundExn");
    private final static QName _SiteInfo_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/patient", "siteInfo");
    private final static QName _SiteInfoResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap/patient", "siteInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.stevens.cs548.clinic.service.web.soap.patient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SiteInfo }
     * 
     */
    public SiteInfo createSiteInfo() {
        return new SiteInfo();
    }

    /**
     * Create an instance of {@link PatientNotFoundExn }
     * 
     */
    public PatientNotFoundExn createPatientNotFoundExn() {
        return new PatientNotFoundExn();
    }

    /**
     * Create an instance of {@link SiteInfoResponse }
     * 
     */
    public SiteInfoResponse createSiteInfoResponse() {
        return new SiteInfoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PatientNotFoundExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/patient", name = "PatientNotFoundExn")
    public JAXBElement<PatientNotFoundExn> createPatientNotFoundExn(PatientNotFoundExn value) {
        return new JAXBElement<PatientNotFoundExn>(_PatientNotFoundExn_QNAME, PatientNotFoundExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/patient", name = "siteInfo")
    public JAXBElement<SiteInfo> createSiteInfo(SiteInfo value) {
        return new JAXBElement<SiteInfo>(_SiteInfo_QNAME, SiteInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap/patient", name = "siteInfoResponse")
    public JAXBElement<SiteInfoResponse> createSiteInfoResponse(SiteInfoResponse value) {
        return new JAXBElement<SiteInfoResponse>(_SiteInfoResponse_QNAME, SiteInfoResponse.class, null, value);
    }

}
