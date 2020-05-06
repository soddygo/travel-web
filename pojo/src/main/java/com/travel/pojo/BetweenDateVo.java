package com.travel.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 时间范围,是个数组,有个2个时间,开始结束时间
 *
 * @author songgc
 * @date 2019-06-19 20:42
 */
@Getter
@Setter
public class BetweenDateVo implements Serializable {

    private Date startDate;
    private Date endDate;

}
