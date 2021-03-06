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
import javax.xml.bind.annotation.XmlType;


/**
 * Bibliographic data of the Physical Item. Output parameter.
 * 
 * <p>Java class for bib_data complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="bib_data">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="mms_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="issn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isbn" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="complete_edition" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="network_numbers" type="{}network_numbers"/>
 *         &lt;element name="place_of_publication" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="publisher_const" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "bib_data", propOrder = {

})
public class BibData {

    @XmlElement(name = "mms_id", required = true)
    protected String mmsId;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String author;
    protected String issn;
    @XmlElement(required = true)
    protected String isbn;
    @XmlElement(name = "complete_edition", required = true)
    protected String completeEdition;
    @XmlElement(name = "network_numbers", required = true)
    protected NetworkNumbers networkNumbers;
    @XmlElement(name = "place_of_publication", required = true)
    protected String placeOfPublication;
    @XmlElement(name = "publisher_const", required = true)
    protected String publisherConst;
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
