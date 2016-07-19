
package edu.stevens.cs548.clinic.service.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DrugTreatmentType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DrugTreatmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dosage" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="prescribing-physician" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugTreatmentType", propOrder = {
    "name",
    "dosage",
    "prescribingPhysician"
})
public class DrugTreatmentType {

    @XmlElement(required = true)
    protected String name;
    protected float dosage;
    @XmlElement(name = "prescribing-physician", required = true)
    protected String prescribingPhysician;

    /**
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取dosage属性的值。
     * 
     */
    public float getDosage() {
        return dosage;
    }

    /**
     * 设置dosage属性的值。
     * 
     */
    public void setDosage(float value) {
        this.dosage = value;
    }

    /**
     * 获取prescribingPhysician属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescribingPhysician() {
        return prescribingPhysician;
    }

    /**
     * 设置prescribingPhysician属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescribingPhysician(String value) {
        this.prescribingPhysician = value;
    }

}
