package com.nullpointer.controller;

import com.nullpointer.client.generated.DailyInfo;
import com.nullpointer.client.generated.DailyInfoSoap;
import com.nullpointer.client.generated.GetCursOnDateXMLResponse;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Created by pyurkin on 10/9/2015.
 */
@Component
public class CbrClient {

    public GetCursOnDateXMLResponse.GetCursOnDateXMLResult getCursOnDateInfoFromCbr(XMLGregorianCalendar date) {
        DailyInfo dailyInfo = new DailyInfo();
        DailyInfoSoap dailyInfoSoap = dailyInfo.getDailyInfoSoap();
        GetCursOnDateXMLResponse.GetCursOnDateXMLResult cursOnDateXML = dailyInfoSoap.getCursOnDateXML(date);
        return cursOnDateXML;
    }

}
