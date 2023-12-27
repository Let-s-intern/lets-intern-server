package com.letsintern.letsintern.global.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");

    public static String dateToString(LocalDateTime date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }
}
