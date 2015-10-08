
package com.nullpointer.client.generated;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * 
 *             \u0412\u0435\u0431 \u0441\u0435\u0440\u0432\u0438\u0441 \u0434\u043b\u044f \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0435\u0436\u0435\u0434\u043d\u0435\u0432\u043d\u044b\u0445 \u0434\u0430\u043d\u043d\u044b\u0445 ver 06.10.2015
 *         
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DailyInfo", targetNamespace = "http://web.cbr.ru/", wsdlLocation = "file:/C:/Users/pyurkin/IdeaProjects/currency-rate-api/api/src/main/java/com/nullpointer/client/DailyInfoWebServ.wsdl")
public class DailyInfo
    extends Service
{

    private final static URL DAILYINFO_WSDL_LOCATION;
    private final static WebServiceException DAILYINFO_EXCEPTION;
    private final static QName DAILYINFO_QNAME = new QName("http://web.cbr.ru/", "DailyInfo");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/Users/pyurkin/IdeaProjects/currency-rate-api/api/src/main/java/com/nullpointer/client/DailyInfoWebServ.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DAILYINFO_WSDL_LOCATION = url;
        DAILYINFO_EXCEPTION = e;
    }

    public DailyInfo() {
        super(__getWsdlLocation(), DAILYINFO_QNAME);
    }

    public DailyInfo(WebServiceFeature... features) {
        super(__getWsdlLocation(), DAILYINFO_QNAME, features);
    }

    public DailyInfo(URL wsdlLocation) {
        super(wsdlLocation, DAILYINFO_QNAME);
    }

    public DailyInfo(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DAILYINFO_QNAME, features);
    }

    public DailyInfo(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DailyInfo(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DailyInfoSoap
     */
    @WebEndpoint(name = "DailyInfoSoap")
    public DailyInfoSoap getDailyInfoSoap() {
        return super.getPort(new QName("http://web.cbr.ru/", "DailyInfoSoap"), DailyInfoSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DailyInfoSoap
     */
    @WebEndpoint(name = "DailyInfoSoap")
    public DailyInfoSoap getDailyInfoSoap(WebServiceFeature... features) {
        return super.getPort(new QName("http://web.cbr.ru/", "DailyInfoSoap"), DailyInfoSoap.class, features);
    }

    /**
     * 
     * @return
     *     returns DailyInfoSoap
     */
    @WebEndpoint(name = "DailyInfoSoap12")
    public DailyInfoSoap getDailyInfoSoap12() {
        return super.getPort(new QName("http://web.cbr.ru/", "DailyInfoSoap12"), DailyInfoSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DailyInfoSoap
     */
    @WebEndpoint(name = "DailyInfoSoap12")
    public DailyInfoSoap getDailyInfoSoap12(WebServiceFeature... features) {
        return super.getPort(new QName("http://web.cbr.ru/", "DailyInfoSoap12"), DailyInfoSoap.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DAILYINFO_EXCEPTION!= null) {
            throw DAILYINFO_EXCEPTION;
        }
        return DAILYINFO_WSDL_LOCATION;
    }

}