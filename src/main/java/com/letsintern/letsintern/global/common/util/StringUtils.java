package com.letsintern.letsintern.global.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtils {

    public static String listToString(List<Integer> integerList) {
        return integerList.stream().map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public static List<Integer> stringToList(String strList) {
        String[] convertedArray = strList.split(",");
        List<Integer> convertedList = new ArrayList<>();

        for(String strNum : convertedArray) {
            convertedList.add(Integer.parseInt(strNum));
        }

        return convertedList;
    }
}
