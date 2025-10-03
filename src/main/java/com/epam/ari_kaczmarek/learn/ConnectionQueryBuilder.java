package com.epam.ari_kaczmarek.learn;

import java.time.LocalDate;
import java.time.LocalTime;

public class ConnectionQueryBuilder {
    private String fromStation;
    private String toStation;
    private LocalDate date;
    private LocalTime time;

    public ConnectionQueryBuilder() {}

    public ConnectionQueryBuilder setFromStation(String fromStation) {
        this.fromStation = fromStation;
        return this;
    }

    public ConnectionQueryBuilder setToStation(String toStation) {
        this.toStation = toStation;
        return this;
    }

    public ConnectionQueryBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ConnectionQueryBuilder setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public ConnectionQuery build() {
        return new ConnectionQuery(fromStation, toStation, date, time);
    }
}