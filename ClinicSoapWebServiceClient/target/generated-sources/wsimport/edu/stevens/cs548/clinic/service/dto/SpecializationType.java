
package edu.stevens.cs548.clinic.service.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>SpecializationType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="SpecializationType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Radiology"/>
 *     &lt;enumeration value="Surgery"/>
 *     &lt;enumeration value="Oncology"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SpecializationType")
@XmlEnum
public enum SpecializationType {

    @XmlEnumValue("Radiology")
    RADIOLOGY("Radiology"),
    @XmlEnumValue("Surgery")
    SURGERY("Surgery"),
    @XmlEnumValue("Oncology")
    ONCOLOGY("Oncology");
    private final String value;

    SpecializationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SpecializationType fromValue(String v) {
        for (SpecializationType c: SpecializationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
