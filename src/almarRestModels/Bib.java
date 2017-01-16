//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.11 at 03:28:59 PM IST 
//


package almarRestModels;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Element;


/**
 * Bibliographic record object
 * 
 * <p>Java class for bib complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bib">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mms_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="record_format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linked_record_id" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="issn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="complete_edition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="network_numbers" type="{}network_numbers" minOccurs="0"/>
 *         &lt;element name="place_of_publication" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="publisher_const" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="holdings" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="link" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="created_by" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="created_date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="last_modified_by" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="last_modified_date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="suppress_from_publishing" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originating_system" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originating_system_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="link" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "bib", propOrder = {
    "mmsId",
    "recordFormat",
    "linkedRecordId",
    "title",
    "author",
    "issn",
    "isbn",
    "completeEdition",
    "networkNumbers",
    "placeOfPublication",
    "publisherConst",
    "holdings",
    "createdBy",
    "createdDate",
    "lastModifiedBy",
    "lastModifiedDate",
    "suppressFromPublishing",
    "originatingSystem",
    "originatingSystemId",
    "any"
})
public class Bib {

    @XmlElement(name = "mms_id")
    protected String mmsId;
    @XmlElement(name = "record_format")
    protected String recordFormat;
    @XmlElement(name = "linked_record_id")
    protected Bib.LinkedRecordId linkedRecordId;
    protected String title;
    protected String author;
    protected String issn;
    protected String isbn;
    @XmlElement(name = "complete_edition")
    protected String completeEdition;
    @XmlElement(name = "network_numbers")
    protected NetworkNumbers networkNumbers;
    @XmlElement(name = "place_of_publication")
    protected String placeOfPublication;
    @XmlElement(name = "publisher_const")
    protected String publisherConst;
    protected Bib.Holdings holdings;
    @XmlElement(name = "created_by")
    protected String createdBy;
    @XmlElement(name = "created_date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar createdDate;
    @XmlElement(name = "last_modified_by")
    protected String lastModifiedBy;
    @XmlElement(name = "last_modified_date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastModifiedDate;
    @XmlElement(name = "suppress_from_publishing")
    protected String suppressFromPublishing;
    @XmlElement(name = "originating_system")
    protected String originatingSystem;
    @XmlElement(name = "originating_system_id")
    protected String originatingSystemId;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "link")
    protected String link;

    /**
     * Gets the value of the mmsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMmsId() {
        return mmsId;
    }

    /**
     * Sets the value of the mmsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMmsId(String value) {
        this.mmsId = value;
    }

    /**
     * Gets the value of the recordFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordFormat() {
        return recordFormat;
    }

    /**
     * Sets the value of the recordFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordFormat(String value) {
        this.recordFormat = value;
    }

    /**
     * Gets the value of the linkedRecordId property.
     * 
     * @return
     *     possible object is
     *     {@link Bib.LinkedRecordId }
     *     
     */
    public Bib.LinkedRecordId getLinkedRecordId() {
        return linkedRecordId;
    }

    /**
     * Sets the value of the linkedRecordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bib.LinkedRecordId }
     *     
     */
    public void setLinkedRecordId(Bib.LinkedRecordId value) {
        this.linkedRecordId = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the issn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssn() {
        return issn;
    }

    /**
     * Sets the value of the issn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssn(String value) {
        this.issn = value;
    }

    /**
     * Gets the value of the isbn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the value of the isbn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsbn(String value) {
        this.isbn = value;
    }

    /**
     * Gets the value of the completeEdition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompleteEdition() {
        return completeEdition;
    }

    /**
     * Sets the value of the completeEdition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompleteEdition(String value) {
        this.completeEdition = value;
    }

    /**
     * Gets the value of the networkNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link NetworkNumbers }
     *     
     */
    public NetworkNumbers getNetworkNumbers() {
        return networkNumbers;
    }

    /**
     * Sets the value of the networkNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link NetworkNumbers }
     *     
     */
    public void setNetworkNumbers(NetworkNumbers value) {
        this.networkNumbers = value;
    }

    /**
     * Gets the value of the placeOfPublication property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceOfPublication() {
        return placeOfPublication;
    }

    /**
     * Sets the value of the placeOfPublication property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceOfPublication(String value) {
        this.placeOfPublication = value;
    }

    /**
     * Gets the value of the publisherConst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisherConst() {
        return publisherConst;
    }

    /**
     * Sets the value of the publisherConst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisherConst(String value) {
        this.publisherConst = value;
    }

    /**
     * Gets the value of the holdings property.
     * 
     * @return
     *     possible object is
     *     {@link Bib.Holdings }
     *     
     */
    public Bib.Holdings getHoldings() {
        return holdings;
    }

    /**
     * Sets the value of the holdings property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bib.Holdings }
     *     
     */
    public void setHoldings(Bib.Holdings value) {
        this.holdings = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the createdDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the value of the createdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedDate(XMLGregorianCalendar value) {
        this.createdDate = value;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedBy(String value) {
        this.lastModifiedBy = value;
    }

    /**
     * Gets the value of the lastModifiedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets the value of the lastModifiedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedDate(XMLGregorianCalendar value) {
        this.lastModifiedDate = value;
    }

    /**
     * Gets the value of the suppressFromPublishing property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuppressFromPublishing() {
        return suppressFromPublishing;
    }

    /**
     * Sets the value of the suppressFromPublishing property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuppressFromPublishing(String value) {
        this.suppressFromPublishing = value;
    }

    /**
     * Gets the value of the originatingSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginatingSystem() {
        return originatingSystem;
    }

    /**
     * Sets the value of the originatingSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginatingSystem(String value) {
        this.originatingSystem = value;
    }

    /**
     * Gets the value of the originatingSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginatingSystemId() {
        return originatingSystemId;
    }

    /**
     * Sets the value of the originatingSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginatingSystemId(String value) {
        this.originatingSystemId = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * {@link Element }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
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
     *       &lt;attribute name="link" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class Holdings {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "link")
        protected String link;

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
     *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    public static class LinkedRecordId {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "type")
        protected String type;

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
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setType(String value) {
            this.type = value;
        }

    }

}