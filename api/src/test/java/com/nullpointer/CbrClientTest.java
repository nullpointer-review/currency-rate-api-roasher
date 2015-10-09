package com.nullpointer;

import com.nullpointer.client.generated.GetCursOnDateXMLResponse;
import com.nullpointer.controller.CbrClient;
import com.nullpointer.controller.CurrencyRateApiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;


import static com.nullpointer.util.utils.getNow;
import static com.nullpointer.util.utils.getXMLGregorianCalendar;
import static org.junit.Assert.assertNotNull;

/**
 * Created by pyurkin on 10/9/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyRateApiApplication.class)
@WebAppConfiguration
public class CbrClientTest {

    @Autowired
    private CbrClient cbrClient;

    @Test
    public void responseNotNullTest() throws DatatypeConfigurationException {
        XMLGregorianCalendar xmlGregorianCalendarNow = getXMLGregorianCalendar(getNow());
        GetCursOnDateXMLResponse.GetCursOnDateXMLResult cursOnDateXML = cbrClient.getCursOnDateInfoFromCbr(xmlGregorianCalendarNow);
        assertNotNull(cursOnDateXML);
    }
}
