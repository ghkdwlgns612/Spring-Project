package com.github.prgrms.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DataTimeUtils {
    public static Timestamp timestampOf(LocalDateTime time) {
        return time == null? null : Timestamp.valueOf(time);
    }
    //localDateTime(현재날짜,시간 정보)를 숫자로 반환하여 저장.
    //timestamp1 = 2021-07-05 12:37:42.1320372
    public static LocalDateTime dateTimeOf(Timestamp timestamp) {
        return timestamp == null? null : timestamp.toLocalDateTime();
    }
    //위와 똑같이 찍힘. 근데 왜 변환을 할까?
}