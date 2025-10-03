package com.epam.ari_kaczmarek.learn;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConnectionQuery {
    private final String fromStation;
    private final String toStation;
    private final LocalDate date;
    private final LocalTime time;

    ConnectionQuery(String fromStation, String toStation, LocalDate date, LocalTime time) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.date = date;
        this.time = time;
    }

    public String getFromStation() {
        return fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }
}