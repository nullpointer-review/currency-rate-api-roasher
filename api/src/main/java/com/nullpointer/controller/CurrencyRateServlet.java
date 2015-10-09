package com.nullpointer.controller;

import com.nullpointer.client.generated.GetCursOnDateXMLResponse;
import com.nullpointer.exception.CurrencyRateApiApplicationException;
import com.nullpointer.model.CurrencyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.DatatypeConfigurationException;
import java.text.ParseException;
import java.util.Date;

import static com.nullpointer.util.utils.fetchCurrencyRate;
import static com.nullpointer.util.utils.getDateFormat;
import static com.nullpointer.util.utils.getXmlGregorianCalendarDate;

/**
 * Created by night wish on 06.10.2015.
 */
@RestController
public class CurrencyRateServlet {

    @Autowired
    private CbrClient cbrClient;

    @RequestMapping("/{code}/{date}")
    public CurrencyRate getCurrencyRate(@PathVariable String code, @PathVariable String date) throws CurrencyRateApiApplicationException {
        Date parsedDate = null;
        try {
            parsedDate = getDateFormat().parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new CurrencyRateApiApplicationException("Ошибка при попытке прочитать дату", e);
        }
        try {
            GetCursOnDateXMLResponse.GetCursOnDateXMLResult cursOnDateInfoFromCbr = cbrClient.getCursOnDateInfoFromCbr(getXmlGregorianCalendarDate(parsedDate));
            return fetchCurrencyRate(code, cursOnDateInfoFromCbr);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new CurrencyRateApiApplicationException("Внутренняя ошибка сервиса", e);
        }
    }

}
