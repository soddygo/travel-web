package com.travel.web.bind;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 时间处理.
 *
 * @author soddygo
 * @since 2018/5/29
 */
@Slf4j
public class LocalDateTimeBinder extends PropertyEditorSupport {


    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if ("".equals(text.trim())) {
            super.setValue(null);
        } else {
            DateTimeFormatter[] dateTimeFormatters = {
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                    DateTimeFormatter.ISO_DATE_TIME

            };

            for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
                try {
                    LocalDateTime date = LocalDateTime.parse(StringUtils.trim(text), dateTimeFormatter);
                    super.setValue(date);

                    return;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }


        }
    }

}
