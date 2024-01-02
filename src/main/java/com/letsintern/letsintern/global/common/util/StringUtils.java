package com.letsintern.letsintern.global.common.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
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

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

    public static String dateToString(LocalDateTime date) {
        return date.format(DATE_TIME_FORMATTER);
    }
}
