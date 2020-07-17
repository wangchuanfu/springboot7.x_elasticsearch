package com.j1.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangchuanfu on 20/7/16.
 */
public class ValidateUtils {
    /**
     * 判断分类id是否有效
     *
     * @param proCatalogId
     * @return
     */
    public static boolean isProCatalogIdAvailable(String proCatalogId) {
        return StringUtils.isNotBlank(proCatalogId) && StringUtils.isNumeric(proCatalogId) && !"0".equals(proCatalogId);
    }
}
