package com.nullpointer;

import com.nullpointer.controller.CurrencyRateApiApplication;
import com.nullpointer.controller.CurrencyRateServlet;
import com.nullpointer.exception.CurrencyRateApiApplicationException;
import com.nullpointer.model.CurrencyRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;

import static com.nullpointer.util.utils.getDateFormat;
import static junit.framework.Assert.assertEquals;

/**
 * Created by pyurkin on 10/9/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyRateApiApplication.class)
@WebAppConfiguration
public class CurrencyRateServletTest {

    public static final String USD_CODE = "USD";

    @Autowired
    private CurrencyRateServlet currencyRateServlet;

    @Test(expected = CurrencyRateApiApplicationException.class)
    public void wrongDateTest() throws CurrencyRateApiApplicationException {
        currencyRateServlet.getCurrencyRate(USD_CODE, "2015-092i4");
    }

    @Test(expected = CurrencyRateApiApplicationException.class)
    public void wrongCurrencyCodeTest() throws CurrencyRateApiApplicationException {
        currencyRateServlet.getCurrencyRate(USD_CODE + "!", "2015-09-24");
    }

    // Straight from the task

    @Test
    public void certainDateTest() throws CurrencyRateApiApplicationException {
        CurrencyRate usdCurrencyRate = currencyRateServlet.getCurrencyRate(USD_CODE, "2015-09-24");
        assertEquals(USD_CODE, usdCurrencyRate.code);
        assertEquals("66.0410", usdCurrencyRate.rate);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DAY_OF_MONTH, 24);
        assertEquals(getDateFormat().format(calendar.getTime()), usdCurrencyRate.date);
    }

    @Test
    public void noDateTest() throws CurrencyRateApiApplicationException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        CurrencyRate usdCurrencyRateForToday = currencyRateServlet.getCurrencyRate(USD_CODE, getDateFormat().format(calendar.getTime()));
        assertEquals(USD_CODE, usdCurrencyRateForToday.code);
    }
}
