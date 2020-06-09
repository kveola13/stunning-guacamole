package com.kveola13.springsecurity.unit;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/units")
public class UnitManagementController {
    private static final List<Unit> UNITS = Arrays.asList(
            new Unit(1, "Witcher"),
            new Unit(2, "Mage"),
            new Unit(3, "Assassin"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_WOLF', 'ROLE_BARD')")
    public List<Unit> getAllUnits() {
        System.out.println("Getting all units");
        return UNITS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('unit:write')")
    public void recruitNewUnit(@RequestBody Unit unit) {
        System.out.println("Recruited unit:");
        System.out.println(unit);
    }

    @DeleteMapping(path = "{unitId}")
    @PreAuthorize("hasAuthority('unit:write')")
    public void deleteUnit(@PathVariable("unitId") Integer unitId) {
        System.out.println("Sold unit:");
        System.out.println(unitId);
    }

    @PutMapping(path = "{unitId}")
    @PreAuthorize("hasAuthority('unit:write')")
    public void updateUnit(@PathVariable("unitId") Integer unitId, @RequestBody Unit unit) {
        System.out.println("Updated unit:");
        System.out.println(String.format("%s, %s", unitId, unit));
    }
}
