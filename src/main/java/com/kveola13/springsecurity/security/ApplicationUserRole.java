package com.kveola13.springsecurity.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.kveola13.springsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    Newb(Sets.newHashSet()),
    Wolf(Sets.newHashSet(STATS_READ, STATS_WRITE, UNIT_READ, UNIT_WRITE)),
    Bard(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
