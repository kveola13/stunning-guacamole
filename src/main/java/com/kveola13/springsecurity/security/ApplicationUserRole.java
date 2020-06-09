package com.kveola13.springsecurity.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(applicationUserPermission -> new SimpleGrantedAuthority(applicationUserPermission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
