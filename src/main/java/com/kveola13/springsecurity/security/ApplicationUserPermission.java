package com.kveola13.springsecurity.security;

public enum ApplicationUserPermission {
    UNIT_READ("unit:read"),
    UNIT_WRITE("unit:write"),
    STATS_READ("stats:read"),
    STATS_WRITE("stats:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
