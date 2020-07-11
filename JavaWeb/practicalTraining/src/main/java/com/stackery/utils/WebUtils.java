package com.stackery.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;


/**
 * 严格要求JavaBean对象中set方法与参数名称一致，网页属性name值与参数名称一致
 */

public class WebUtils {

    /**
     * 将Map键值对注入对应JavaBean属性
     * @param params 传入参数为Map，保证JavaWeb三层架构中均可使用
     * @param bean JavaBean对象
     * @return 注入后的JavaBean对象
     */
    public static <T> T copyParamToBean(Map params, T bean){
        try {
            BeanUtils.populate(bean, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 将字符串转换为int类型
     * @param strInt
     * @return
     */
    public static int parseInt(String strInt){
        return Integer.parseInt(strInt);
    }

}
