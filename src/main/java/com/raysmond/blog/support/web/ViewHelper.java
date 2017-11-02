package com.raysmond.blog.support.web;


import com.raysmond.blog.services.AppSetting;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;
/**
 * @author Raysmond<i@raysmond.com>
 */
@Service
@JadeHelper("viewHelper")
public class ViewHelper {

    private static DateFormatSymbols ruDateFormatSymbolsFull = new DateFormatSymbols(){
        @Override
        public String[] getMonths() {
            return new String[]{"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
        }
    };
    private static DateFormatSymbols ruDateFormatSymbolsShort = new DateFormatSymbols(){
        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};
        }
    };

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMMM dd, yyyy", ViewHelper.ruDateFormatSymbolsFull);
    private static final SimpleDateFormat DATE_FORMAT_MONTH_DAY = new SimpleDateFormat("MMM dd", ViewHelper.ruDateFormatSymbolsShort);

    private AppSetting appSetting;

    private String applicationEnv;


    @Autowired
    public ViewHelper(AppSetting appSetting){
        this.appSetting = appSetting;
    }

    private long startTime;

    public long getResponseTime(){
        return System.currentTimeMillis() - startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getFormattedDate(Date date){
        return date == null ? "" : DATE_FORMAT.format(date);
    }

    public String getMonthAndDay(Date date){
        return date == null ? "" : DATE_FORMAT_MONTH_DAY.format(date);
    }

    public String metaTitle(String title){
        return title + " · " + appSetting.getSiteName();
    }

    public String getApplicationEnv() {
        return applicationEnv;
    }

    public void setApplicationEnv(String applicationEnv) {
        this.applicationEnv = applicationEnv;
    }
}
