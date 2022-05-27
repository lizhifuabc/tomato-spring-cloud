package com.tomato.id;

import com.tomato.utils.date.SystemClock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * SystemClock
 * 1653632762851
 * 1653632771433
 * @author lizhifu
 * @date 2022/5/27
 */
public class SystemClockTest {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        String res =  date + 100 + String.format("%0" + 6 + "d", 1);
        System.out.println(res);
        System.out.println(SystemClock.now());
    }
}
