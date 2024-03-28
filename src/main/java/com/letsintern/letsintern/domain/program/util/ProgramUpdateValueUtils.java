package com.letsintern.letsintern.domain.program.util;

import java.util.Objects;

public class ProgramUpdateValueUtils {
    public static <T> T updateValue(T currentValue, T newValue) {
        if (Objects.isNull(newValue)) {
            return currentValue;
        } else {
            return newValue;
        }
    }
}
