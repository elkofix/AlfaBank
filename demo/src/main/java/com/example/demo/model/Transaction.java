package com.example.demo.model;

import java.time.LocalDate;

public class Transaction {
    public double getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    private double value;

    private String description;

    private Type type;

    private LocalDate date;

    public Transaction(double value, String description, Type type, LocalDate date) {
        this.value = value;
        this.description = description;
        this.type = type;
        this.date = date;
    }


}
