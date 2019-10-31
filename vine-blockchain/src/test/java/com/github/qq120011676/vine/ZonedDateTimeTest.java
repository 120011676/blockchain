package com.github.qq120011676.vine;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ZonedDateTimeTest {
    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_ORDINAL_DATE));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("")));
        System.out.println(ZonedDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));

    }
}
