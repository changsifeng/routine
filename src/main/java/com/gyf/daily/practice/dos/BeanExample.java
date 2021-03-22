package com.gyf.daily.practice.dos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gyf
 * @date 2021/3/22
 */
public class BeanExample {
    public static String upper(String abc) {
        return abc.toUpperCase();
    }
    public boolean anyContains(String str, String searchStr) {

        char[] s = str.toCharArray();
        for (char c : s) {
            if (searchStr.contains(c+"")) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(String source,String target){
        String[] split = source.split(",");
        List<String> s = Arrays.asList(split);
        int size1 = s.size();
        List<String> collect = s.stream().collect(Collectors.toList());

        String[] s1 = target.split(",");
        List<String> strings = Arrays.asList(s1);
        List<String> collect1 = strings.stream().collect(Collectors.toList());

        collect.retainAll(collect1);
        if (collect.size() == size1){
            return true;
        }
        return false;
    }


}
