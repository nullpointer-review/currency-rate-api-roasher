package com.nullpointer.util;

import com.nullpointer.client.generated.GetCursOnDateXMLResponse;
import com.nullpointer.exception.CurrencyRateApiApplicationException;
import com.nullpointer.model.CurrencyRate;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import com.sun.org.apache.xerces.internal.dom.TextImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pyurkin on 10/8/2015.
 */
public class utils {

    /**
     * Fetches currency rate from cbr's result
     * @param code
     * @param result
     * @return
     */
    public static CurrencyRate fetchCurrencyRate(String code, GetCursOnDateXMLResponse.GetCursOnDateXMLResult result) throws CurrencyRateApiApplicationException {
        ElementNSImpl elementNS = (ElementNSImpl) result.getContent().get(0);
        // searching for date
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.code = code;
        try {
            Date onDate = getCbrFormat().parse(elementNS.getAttribute("OnDate"));
            currencyRate.date = getDateFormat().format(onDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new CurrencyRateApiApplicationException("Внутренняя ошибка приложения: Ошибка при чтении даты из ответа ЦБР.", e);
        }
        // searching for rate
        NodeList vchCodeNodeList = elementNS.getElementsByTagName("VchCode");
        for (int currentNode = 0; currentNode < vchCodeNodeList.getLength(); currentNode++) {
            Node currentVchNode = vchCodeNodeList.item(currentNode);
            String currentCode = currentVchNode.getFirstChild().getNodeValue();
            if (currentCode.equalsIgnoreCase(code)) {
                Node parentNode = currentVchNode.getParentNode();
                NodeList neighborNodes = parentNode.getChildNodes();
                for (int currentNeighborNumber = 0; currentNeighborNumber < neighborNodes.getLength(); currentNeighborNumber++) {
                    Node currentNeighbor = neighborNodes.item(currentNeighborNumber);
                    if (currentNeighbor.getNodeName().equalsIgnoreCase("Vcurs")) {
                        currencyRate.rate = currentNeighbor.getFirstChild().getNodeValue();
                        return currencyRate;
                    }
                }
            }
        }
        throw new CurrencyRateApiApplicationException("Нету данных по такому коду валюты");
    }

    public static XMLGregorianCalendar getXmlGregorianCalendarDate(Date date) throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        return getXMLGregorianCalendar(gregorianCalendar);
    }

    public static XMLGregorianCalendar getXMLGregorianCalendar(GregorianCalendar gregorianCalendar) throws DatatypeConfigurationException {
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now = datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        now.setTimezone(0);
        return now;
    }

    public static DateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    private static DateFormat getCbrFormat() {
        return new SimpleDateFormat("yyyyMMdd");
    }
}
