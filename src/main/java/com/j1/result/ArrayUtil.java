package com.j1.result;

import com.j1.common.base.Page;

import java.util.*;

/**
 * Created by wangchuanfu on 20/8/7.
 */
public class ArrayUtil {

    @SuppressWarnings("unchecked")
    public static ArrayList<Map<String, Object>> arrayToObj(Object obj) {
        ArrayList<Map<String, Object>> arr = null;
        if (obj != null && obj instanceof java.util.List<?>)
            return arr = (ArrayList<Map<String, Object>>) obj;
        return arr;
    }


    public static void main(String[] args) {

    }

    /**
     * @param @param  lists
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: resultIsEmp
     * @Description: 判断返回列表数据是否为空
     */
    public static <T> boolean resultIsEmp(ServiceMessage<List<T>> lists) {
        if (lists == null || isEmp(lists.getResult())) {
            return true;
        }
        return false;
    }

    /**
     * @param @param  lists
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: resultIsEmpWithPage
     * @Description: 判断返回列表数据是否为空(带Page类型数据 ）
     */
    public static <T> boolean resultIsEmpWithPage(ServiceMessage<Page<T>> lists) {
        if (lists == null || lists.getResult() == null || isEmp(lists.getResult().getData())) {
            return true;
        }
        return false;
    }

    /**
     * @param @param  lists
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isEmp
     * @Description: 判断是否为空数组
     */
    public static boolean isEmp(Collection<?> lists) {
        if (lists == null || lists.isEmpty()) {
            return true;
        }
        return false;
    }


    /**
     * @param @param  lists
     * @param @return 设定文件
     * @return Integer 返回类型
     * @throws
     * @Title: findFirstFromList
     * @Description: 查询list列表中第一个位置对象
     */
    public static <T> T findFirstFromList(List<T> lists) {
        if (lists == null || lists.size() < 1) {
            return null;
        }
        for (T s : lists) {
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    /**
     * @param @param  lists
     * @param @return 设定文件
     * @return Integer 返回类型
     * @throws
     * @Title: findFirstIDFromList
     * @Description: 查询list列表中第一个位置对象ID
     */
    public static <T> T findFirstIDFromList(List<T> lists) {
        if (lists == null || lists.size() < 1) {
            return null;
        }
        for (T s : lists) {
            if (s != null) {
                return s;
            }
        }
        return null;
    }

    /**
     * @param @param dest
     * @param @param src
     * @param @param filter  设定文件
     * @return void 返回类型
     * @throws
     * @Title: filter
     * @Description: 把list中符合要求的数据添加到另一个list
     */
    public static <T> List<T> filter(List<T> src, T key, Comparator<T> filter) {
        List<T> dest = new ArrayList<T>();
        if (filter == null) {
            Collections.copy(dest, src);
        } else {
            for (T tmp : src) {
                if (filter.compare(tmp, key) == 0) {
                    dest.add(tmp);
                }
            }
        }
        return dest;
    }


    static interface Filter<T> {

    }

    /**
     * @param @param  val
     * @param @param  arrays
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: isInclude
     * @Description: 检查字符串是否在数组中
     */
    public static boolean isInclude(String val, final String[] arrays) {
        if (arrays == null || val == null) return false;
        for (String s : arrays) {
            if (val.equals(s)) return true;
        }
        return false;
    }
}
