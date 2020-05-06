package com.travel.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * page工具类
 *
 * @author songgc
 * @date 2019-03-19 17:36
 */
public class PageUtil {

    private static int maxPageSize = 500;

    /**
     * 构建,限制page
     *
     * @param pageNo   当前页
     * @param pageSize 页大小
     * @return page
     */
    public static <T> Page<T> build(Integer pageNo, Integer pageSize) {

        int tempPageNo = pageNo == null ? 1 : pageNo;
        int tempPageSize = pageSize == null ? 30 : pageSize;

        if (tempPageSize > maxPageSize) {
            tempPageSize = maxPageSize;
        }

        if (tempPageNo <= 0) {
            tempPageNo = 1;
        }

        return new Page<>(tempPageNo, tempPageSize);
    }
}
