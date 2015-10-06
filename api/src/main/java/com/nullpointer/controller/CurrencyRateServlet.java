package com.nullpointer.controller;

import com.nullpointer.model.CurrencyRate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by night wish on 06.10.2015.
 */
@RestController
public class CurrencyRateServlet {

    @RequestMapping("/{code}/{date}")
    public CurrencyRate getCurrencyRate(@PathVariable String code, @PathVariable String date) {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.code = code;
        currencyRate.rate = "RATE";
        currencyRate.date = new Date();
        return currencyRate;
    }
}
