
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;


/**
 * <p>patientGetTreatmentResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="patientGetTreatmentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cs548.stevens.edu/clinic/service/dto}treatment-dto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientGetTreatmentResponse", propOrder = {
    "treatmentDto"
})
public class PatientGetTreatmentResponse {

    @XmlElement(name = "treatment-dto", namespace = "http://cs548.stevens.edu/clinic/service/dto")
    protected TreatmentDto treatmentDto;

    /**
     * 获取treatmentDto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link TreatmentDto }
     *     
     */
    public TreatmentDto getTreatmentDto() {
        return treatmentDto;
    }

    /**
     * 设置treatmentDto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link TreatmentDto }
     *     
     */
    public void setTreatmentDto(TreatmentDto value) {
        this.treatmentDto = value;
    }

}
