package com.travel.web.bind;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * 时间处理.
 *
 * @author soddygo
 * @since 2018/5/29
 */
@Slf4j
public class DateBinder extends PropertyEditorSupport {


    private boolean nullable = true;

    public DateBinder(boolean nullable) {
        this.nullable = nullable;
    }

    @Override
    public String getAsText() {
        Date date = (Date) getValue();
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {
            if ("".equals(text.trim())) {
                super.setValue(null);
            } else {
                Date date = DateUtils.parseDate(StringUtils.trim(text), "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
                        "yyyyMMddHHmmss", "yyyyMMdd");
                super.setValue(date);
            }
        } catch (ParseException e) {
            log.warn("数据绑定为时间对象失败:绑定时间字符串【{}】", text);
            log.warn("转换时间失败", e);
            if (nullable) {
                super.setValue(null);
            }
        }
    }

}
