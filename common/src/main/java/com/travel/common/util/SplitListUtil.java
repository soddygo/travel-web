package com.travel.common.util;

/**
 * list切割工具
 *
 * @author songgc
 * @date 2019/9/25 5:56 PM
 */
public class SplitListUtil {

    private static final int MAX_NUMBER = 100;

    /**
     * 计算切分次数
     */
    public static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    /**
     * 计算切分次数
     *
     * @param size      list大小
     * @param maxNumber 切分大小
     * @return 切分次数
     */
    public static Integer countStep(Integer size, int maxNumber) {
        return (size + maxNumber - 1) / maxNumber;
    }


}
