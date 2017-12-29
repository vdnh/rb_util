//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.12.22 at 12:48:44 PM EST 
//


package weatherbug.weatherreportservice.rss;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
import org.w3c.dom.Element;


/**
 * <p>Java class for RssChannel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RssChannel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *           &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="language" type="{http://www.w3.org/2001/XMLSchema}language" minOccurs="0"/>
 *           &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="managingEditor" type="{}EmailAddress" minOccurs="0"/>
 *           &lt;element name="webMaster" type="{}EmailAddress" minOccurs="0"/>
 *           &lt;element name="pubDate" type="{}Rfc822FormatDate" minOccurs="0"/>
 *           &lt;element name="lastBuildDate" type="{}Rfc822FormatDate" minOccurs="0"/>
 *           &lt;element name="category" type="{}Category" minOccurs="0"/>
 *           &lt;element name="generator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="docs" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *           &lt;element name="cloud" type="{}Cloud" minOccurs="0"/>
 *           &lt;element name="ttl" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" minOccurs="0"/>
 *           &lt;element name="image" type="{}Image" minOccurs="0"/>
 *           &lt;element name="textInput" type="{}TextInput" minOccurs="0"/>
 *           &lt;element name="skipHours" type="{}SkipHoursList" minOccurs="0"/>
 *           &lt;element name="skipDays" type="{}SkipDaysList" minOccurs="0"/>
 *           &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;element name="item" type="{}RssItem" maxOccurs="unbounded"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RssChannel", propOrder = {
    "titleOrLinkOrDescription",
    "item",
    "any"
})
public class RssChannel {

    @XmlElementRefs({
        @XmlElementRef(name = "webMaster", type = JAXBElement.class),
        @XmlElementRef(name = "lastBuildDate", type = JAXBElement.class),
        @XmlElementRef(name = "cloud", type = JAXBElement.class),
        @XmlElementRef(name = "skipDays", type = JAXBElement.class),
        @XmlElementRef(name = "title", type = JAXBElement.class),
        @XmlElementRef(name = "language", type = JAXBElement.class),
        @XmlElementRef(name = "skipHours", type = JAXBElement.class),
        @XmlElementRef(name = "managingEditor", type = JAXBElement.class),
        @XmlElementRef(name = "category", type = JAXBElement.class),
        @XmlElementRef(name = "link", type = JAXBElement.class),
        @XmlElementRef(name = "image", type = JAXBElement.class),
        @XmlElementRef(name = "copyright", type = JAXBElement.class),
        @XmlElementRef(name = "pubDate", type = JAXBElement.class),
        @XmlElementRef(name = "description", type = JAXBElement.class),
        @XmlElementRef(name = "generator", type = JAXBElement.class),
        @XmlElementRef(name = "textInput", type = JAXBElement.class),
        @XmlElementRef(name = "docs", type = JAXBElement.class),
        @XmlElementRef(name = "ttl", type = JAXBElement.class)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> titleOrLinkOrDescription;
    @XmlElement(required = true)
    protected List<RssItem> item;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the titleOrLinkOrDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the titleOrLinkOrDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitleOrLinkOrDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Cloud }{@code >}
     * {@link JAXBElement }{@code <}{@link SkipDaysList }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link SkipHoursList }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Category }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link Image }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TextInput }{@code >}
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * 
     * 
     */
    public List<Object> getTitleOrLinkOrDescription() {
        if (titleOrLinkOrDescription == null) {
            titleOrLinkOrDescription = new ArrayList<Object>();
        }
        return this.titleOrLinkOrDescription;
    }

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RssItem }
     * 
     * 
     */
    public List<RssItem> getItem() {
        if (item == null) {
            item = new ArrayList<RssItem>();
        }
        return this.item;
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
     * {@link Element }
     * {@link Object }
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
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
