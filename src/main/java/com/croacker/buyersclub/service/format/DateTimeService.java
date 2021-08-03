package com.croacker.buyersclub.service.format;

import java.time.LocalDateTime;

public interface DateTimeService {

    LocalDateTime stringToLocalDateTime(String str);

    int dateTimeToEpoch(LocalDateTime date);

    LocalDateTime fromEpoch(int datetime);

    String localDateTimeToString(LocalDateTime dateTime);
}
