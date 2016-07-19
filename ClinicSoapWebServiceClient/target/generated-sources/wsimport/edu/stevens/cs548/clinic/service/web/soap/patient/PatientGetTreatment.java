
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>patientGetTreatment complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="patientGetTreatment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="arg1" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientGetTreatment", propOrder = {
    "arg0",
    "arg1"
})
public class PatientGetTreatment {

    protected long arg0;
    protected long arg1;

    /**
     * 获取arg0属性的值。
     * 
     */
    public long getArg0() {
        return arg0;
    }

    /**
     * 设置arg0属性的值。
     * 
     */
    public void setArg0(long value) {
        this.arg0 = value;
    }

    /**
     * 获取arg1属性的值。
     * 
     */
    public long getArg1() {
        return arg1;
    }

    /**
     * 设置arg1属性的值。
     * 
     */
    public void setArg1(long value) {
        this.arg1 = value;
    }

}
