package com.kveola13.springsecurity.unit;

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
    public List<Unit> getAllUnits() {
        return UNITS;
    }

    @PostMapping
    public void recruitNewUnit(@RequestBody Unit unit) {
        System.out.println(unit);
    }

    @DeleteMapping(path = "{unitId}")
    public void deleteUnit(@PathVariable("unitId") Integer unitId) {
        System.out.println(unitId);
    }

    @PutMapping(path = "{unitId}")
    public void updateUnit(@PathVariable("{unitId}") Integer unitId, @RequestBody Unit unit) {
        System.out.println(String.format("%s, %s", unitId, unit));
    }
}
