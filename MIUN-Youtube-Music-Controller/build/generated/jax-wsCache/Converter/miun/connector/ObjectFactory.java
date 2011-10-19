
package miun.connector;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the miun.connector package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetM3UStream_QNAME = new QName("http://player.miun/", "getM3UStream");
    private final static QName _GetM3UStreamResponse_QNAME = new QName("http://player.miun/", "getM3UStreamResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: miun.connector
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetM3UStreamResponse }
     * 
     */
    public GetM3UStreamResponse createGetM3UStreamResponse() {
        return new GetM3UStreamResponse();
    }

    /**
     * Create an instance of {@link GetM3UStream }
     * 
     */
    public GetM3UStream createGetM3UStream() {
        return new GetM3UStream();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetM3UStream }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "getM3UStream")
    public JAXBElement<GetM3UStream> createGetM3UStream(GetM3UStream value) {
        return new JAXBElement<GetM3UStream>(_GetM3UStream_QNAME, GetM3UStream.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetM3UStreamResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://player.miun/", name = "getM3UStreamResponse")
    public JAXBElement<GetM3UStreamResponse> createGetM3UStreamResponse(GetM3UStreamResponse value) {
        return new JAXBElement<GetM3UStreamResponse>(_GetM3UStreamResponse_QNAME, GetM3UStreamResponse.class, null, value);
    }

}
