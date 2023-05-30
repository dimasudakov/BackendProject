package dev.dima.authservice.models.enums;

public enum Status {
    ACTIVE("Active"),
    BLOCKED("Blocked");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}