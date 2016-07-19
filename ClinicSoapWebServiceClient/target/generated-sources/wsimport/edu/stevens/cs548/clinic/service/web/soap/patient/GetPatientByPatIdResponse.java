
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import edu.stevens.cs548.clinic.service.dto.PatientDto;


/**
 * <p>getPatientByPatIdResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getPatientByPatIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cs548.stevens.edu/clinic/service/dto}patient-dto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPatientByPatIdResponse", propOrder = {
    "patientDto"
})
public class GetPatientByPatIdResponse {

    @XmlElement(name = "patient-dto", namespace = "http://cs548.stevens.edu/clinic/service/dto")
    protected PatientDto patientDto;

    /**
     * 获取patientDto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link PatientDto }
     *     
     */
    public PatientDto getPatientDto() {
        return patientDto;
    }

    /**
     * 设置patientDto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDto }
     *     
     */
    public void setPatientDto(PatientDto value) {
        this.patientDto = value;
    }

}
