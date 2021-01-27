package com.croacker.buyersclub.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

@Service
public class DateTimeServiceImpl implements DateTimeService {
    @Override
    public LocalDateTime stringToLocalDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }

    @Override
    public int dateTimeToEpoch(LocalDateTime date) {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.systemDefault();
        return (int) date.toEpochSecond(zoneId.getRules().getOffset(instant));
    }

    public LocalDateTime fromEpoch(int datetime) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(datetime), TimeZone.getDefault().toZoneId());
    }
}
