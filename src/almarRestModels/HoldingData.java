//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.18 at 11:16:59 AM IST 
//


package almarRestModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Information of the Holding that this Physical Item belongs to.
 * 
 * <p>Java class for holding_data complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="holding_data">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="holding_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="call_number_type" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="call_number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accession_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="copy_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="in_temp_location" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="temp_library" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="temp_location" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="temp_call_number_type" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="temp_call_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="temp_policy" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="due_back_date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/all>
 *       &lt;attribute name="link" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "holding_data", propOrder = {

})
public class HoldingData {

    @XmlElement(name = "holding_id", required = true)
    protected String holdingId;
    @XmlElement(name = "call_number_type")
    protected HoldingData.CallNumberType callNumberType;
    @XmlElement(name = "call_number", required = true)
    protected String callNumber;
    @XmlElement(name = "accession_number")
    protected String accessionNumber;
    @XmlElement(name = "copy_id", required = true)
    protected String copyId;
    @XmlElement(name = "in_temp_location")
    protected Boolean inTempLocation;
    @XmlElement(name = "temp_library")
    protected HoldingData.TempLibrary tempLibrary;
    @XmlElement(name = "temp_location")
    protected HoldingData.TempLocation tempLocation;
    @XmlElement(name = "temp_call_number_type")
    protected HoldingData.TempCallNumberType tempCallNumberType;
    @XmlElement(name = "temp_call_number")
    protected String tempCallNumber;
    @XmlElement(name = "temp_policy")
    protected HoldingData.TempPolicy tempPolicy;
    @XmlElement(name = "due_back_date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dueBackDate;
    @XmlAttribute(name = "link")
    protected String link;

    /**
     * Gets the value of the holdingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoldingId() {
        return holdingId;
    }

    /**
     * Sets the value of the holdingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoldingId(String value) {
        this.holdingId = value;
    }

    /**
     * Gets the value of the callNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link HoldingData.CallNumberType }
     *     
     */
    public HoldingData.CallNumberType getCallNumberType() {
        return callNumberType;
    }

    /**
     * Sets the value of the callNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldingData.CallNumberType }
     *     
     */
    public void setCallNumberType(HoldingData.CallNumberType value) {
        this.callNumberType = value;
    }

    /**
     * Gets the value of the callNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallNumber() {
        return callNumber;
    }

    /**
     * Sets the value of the callNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallNumber(String value) {
        this.callNumber = value;
    }

    /**
     * Gets the value of the accessionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessionNumber() {
        return accessionNumber;
    }

    /**
     * Sets the value of the accessionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessionNumber(String value) {
        this.accessionNumber = value;
    }

    /**
     * Gets the value of the copyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopyId() {
        return copyId;
    }

    /**
     * Sets the value of the copyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopyId(String value) {
        this.copyId = value;
    }

    /**
     * Gets the value of the inTempLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInTempLocation() {
        return inTempLocation;
    }

    /**
     * Sets the value of the inTempLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInTempLocation(Boolean value) {
        this.inTempLocation = value;
    }

    /**
     * Gets the value of the tempLibrary property.
     * 
     * @return
     *     possible object is
     *     {@link HoldingData.TempLibrary }
     *     
     */
    public HoldingData.TempLibrary getTempLibrary() {
        return tempLibrary;
    }

    /**
     * Sets the value of the tempLibrary property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldingData.TempLibrary }
     *     
     */
    public void setTempLibrary(HoldingData.TempLibrary value) {
        this.tempLibrary = value;
    }

    /**
     * Gets the value of the tempLocation property.
     * 
     * @return
     *     possible object is
     *     {@link HoldingData.TempLocation }
     *     
     */
    public HoldingData.TempLocation getTempLocation() {
        return tempLocation;
    }

    /**
     * Sets the value of the tempLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldingData.TempLocation }
     *     
     */
    public void setTempLocation(HoldingData.TempLocation value) {
        this.tempLocation = value;
    }

    /**
     * Gets the value of the tempCallNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link HoldingData.TempCallNumberType }
     *     
     */
    public HoldingData.TempCallNumberType getTempCallNumberType() {
        return tempCallNumberType;
    }

    /**
     * Sets the value of the tempCallNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldingData.TempCallNumberType }
     *     
     */
    public void setTempCallNumberType(HoldingData.TempCallNumberType value) {
        this.tempCallNumberType = value;
    }

    /**
     * Gets the value of the tempCallNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTempCallNumber() {
        return tempCallNumber;
    }

    /**
     * Sets the value of the tempCallNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTempCallNumber(String value) {
        this.tempCallNumber = value;
    }

    /**
     * Gets the value of the tempPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link HoldingData.TempPolicy }
     *     
     */
    public HoldingData.TempPolicy getTempPolicy() {
        return tempPolicy;
    }

    /**
     * Sets the value of the tempPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link HoldingData.TempPolicy }
     *     
     */
    public void setTempPolicy(HoldingData.TempPolicy value) {
        this.tempPolicy = value;
    }

    /**
     * Gets the value of the dueBackDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueBackDate() {
        return dueBackDate;
    }

    /**
     * Sets the value of the dueBackDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueBackDate(XMLGregorianCalendar value) {
        this.dueBackDate = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class CallNumberType {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "desc")
        protected String desc;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class TempCallNumberType {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "desc")
        protected String desc;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class TempLibrary {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "desc")
        protected String desc;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class TempLocation {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "desc")
        protected String desc;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="desc" type="{http://www.w3.org/2001/XMLSchema}string" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class TempPolicy {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "desc")
        protected String desc;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the desc property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDesc() {
            return desc;
        }

        /**
         * Sets the value of the desc property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDesc(String value) {
            this.desc = value;
        }

    }

}
