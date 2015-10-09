package com.nullpointer.controller;

import com.nullpointer.client.generated.GetCursOnDateXMLResponse;
import com.nullpointer.exception.CurrencyRateApiApplicationException;
import com.nullpointer.model.CurrencyRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import static com.nullpointer.util.utils.*;

/**
 * Created by night wish on 06.10.2015.
 */
@RestController
public class CurrencyRateServlet {

    @Autowired
    private CbrClient cbrClient;

    @RequestMapping(value = {"/{code}/{date}", "/{code}"})
    public CurrencyRate getCurrencyRate(@PathVariable Map<String, String> pathVariables) throws CurrencyRateApiApplicationException {

        String code = pathVariables.get("code");
        String date = pathVariables.get("date");

        XMLGregorianCalendar dateForCbr = getDateForCbr(date);
        GetCursOnDateXMLResponse.GetCursOnDateXMLResult cursOnDateInfoFromCbr = cbrClient.getCursOnDateInfoFromCbr(dateForCbr);
        return fetchCurrencyRate(code, cursOnDateInfoFromCbr);
    }

    /**
     * Returns input date or tomorrows if input is null
     *
     * @param date
     * @return
     */
    private XMLGregorianCalendar getDateForCbr(String date) throws CurrencyRateApiApplicationException {
        try {
            if (date == null) {
                GregorianCalendar calendar = new GregorianCalendar();
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                return getXMLGregorianCalendar(calendar);
            } else {
                Date parsedDate = null;
                try {
                    parsedDate = getDateFormat().parse(date);
                    return getXmlGregorianCalendarDate(parsedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new CurrencyRateApiApplicationException("Ошибка при чтении даты", e);
                }
            }
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            throw new CurrencyRateApiApplicationException("Внутренняя ошибка сервиса", e);
        }
    }
}
