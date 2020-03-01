package com.example.clarence.utillibrary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 数组工具类
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: com.youdiandiancn.application.uitls.SecretUtils.ArraysUtils.java
 * @author: chenph
 * @date: 2016-09-01 14:24
 */
public class ArraysUtils {
    /**
     * 是否为空
     *
     * @param list
     * @return
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 是否不为空
     *
     * @param list
     * @return
     */
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    /**
     * 获取数量，可为null
     *
     * @param list
     * @return
     */
    public static int size(List list) {
        return list == null ? 0 : list.size();
    }

    /**
     * LIST数据不重复
     *
     * @param list
     * @return
     */
    public static List removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        return newList;
    }

    /**
     * 查找最接近目标值的数，并返回
     *
     * @param array
     * @param targetNum
     * @return
     */
    public static Integer binarysearchKey(Object[] array, int targetNum) {
        //Object[] array = temp.clone();
        Arrays.sort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        int targetindex = 0;
        int left = 0, right = 0;
        for (right = array.length - 1; left != right; ) {
            int midIndex = (right + left) / 2;
            int mid = (right - left);
            int midValue = (Integer) array[midIndex];
            if (targetNum == midValue) {
                return midValue;
            }

            if (targetNum > midValue) {
                left = midIndex;
                targetindex = midValue;
            } else {
                right = midIndex;
            }

            if (mid <= 1) {
                break;
            }
        }
        int rightnum = ((Integer) array[right]).intValue();
        int leftnum = ((Integer) array[left]).intValue();
        int ret = Math.abs((rightnum - leftnum) / 2) > Math.abs(rightnum - targetNum) ? rightnum : leftnum;
        if (targetNum > leftnum && ret > targetNum) {
            ret = targetindex;
        } else if (targetNum > rightnum) {
            ret = rightnum;
        }
        return ret;
    }
}
