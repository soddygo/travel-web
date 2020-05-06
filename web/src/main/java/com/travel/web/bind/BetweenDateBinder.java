package com.travel.web.bind;

import com.dmall.rdp.voucher.pojo.vo.voucher.BetweenDateVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

/**
 * GMT时间处理.
 *
 * @author soddygo
 * @since 2018/5/29
 */
@Slf4j
public class BetweenDateBinder extends PropertyEditorSupport {


    private boolean nullable = true;

    public BetweenDateBinder(boolean nullable) {
        this.nullable = nullable;
    }


    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {
            if ("".equals(text.trim())) {
                super.setValue(null);
            } else {

                String[] dateTimes = text.split(",");
                Date date = DateUtils.parseDate(dateTimes[0], "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
                        "yyyyMMddHHmmss", "yyyyMMdd");
                Date date2 = DateUtils.parseDate(dateTimes[1], "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
                        "yyyyMMddHHmmss", "yyyyMMdd");

                BetweenDateVo betweenDateVo = new BetweenDateVo();
                betweenDateVo.setStartDate(date);
                betweenDateVo.setEndDate(date2);

                super.setValue(betweenDateVo);
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
