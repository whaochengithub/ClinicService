
package edu.stevens.cs548.clinic.service.dto;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RadiologyType complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RadiologyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dates" type="{http://cs548.stevens.edu/clinic/service/dto}RadDateType" maxOccurs="unbounded"/>
 *         &lt;element name="radiologist" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadiologyType", propOrder = {
    "dates",
    "radiologist"
})
public class RadiologyType {

    @XmlElement(required = true)
    protected List<RadDateType> dates;
    @XmlElement(required = true)
    protected String radiologist;

    /**
     * Gets the value of the dates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RadDateType }
     * 
     * 
     */
    public List<RadDateType> getDates() {
        if (dates == null) {
            dates = new ArrayList<RadDateType>();
        }
        return this.dates;
    }

    /**
     * 获取radiologist属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiologist() {
        return radiologist;
    }

    /**
     * 设置radiologist属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiologist(String value) {
        this.radiologist = value;
    }

}
