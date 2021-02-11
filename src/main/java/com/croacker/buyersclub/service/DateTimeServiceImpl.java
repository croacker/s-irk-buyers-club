package com.croacker.buyersclub.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Service
public class DateTimeServiceImpl implements DateTimeService {

    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("UTC+8");

    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final DateTimeFormatter OFD_DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime stringToLocalDateTime(String str) {
        return LocalDateTime.parse(str, OFD_DATE_TIME_FORMATTER);
    }

    @Override
    public int dateTimeToEpoch(LocalDateTime date) {
        Instant instant = Instant.now();
        return (int) date.toEpochSecond(DEFAULT_ZONE_ID.getRules().getOffset(instant));
    }

    @Override
    public LocalDateTime fromEpoch(int datetime) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(datetime), TimeZone.getDefault().toZoneId());
    }

    @Override
    public String localDateTimeToString(LocalDateTime dateTime){
        return dateTime.format(DATE_TIME_FORMATTER);
    }

}
