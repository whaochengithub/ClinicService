
package edu.stevens.cs548.clinic.service.web.soap.provider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;


/**
 * <p>addProvider complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="addProvider">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cs548.stevens.edu/clinic/service/dto}provider-dto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addProvider", propOrder = {
    "providerDto"
})
public class AddProvider {

    @XmlElement(name = "provider-dto", namespace = "http://cs548.stevens.edu/clinic/service/dto")
    protected ProviderDto providerDto;

    /**
     * 获取providerDto属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ProviderDto }
     *     
     */
    public ProviderDto getProviderDto() {
        return providerDto;
    }

    /**
     * 设置providerDto属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderDto }
     *     
     */
    public void setProviderDto(ProviderDto value) {
        this.providerDto = value;
    }

}
