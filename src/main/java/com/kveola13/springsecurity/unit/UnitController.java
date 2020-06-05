package com.kveola13.springsecurity.unit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/units")
public class UnitController {

    private static final List<Unit> UNITS = Arrays.asList(
            new Unit(1, "Witcher"),
            new Unit(2, "Mage"),
            new Unit(3, "Assassin"));

    @GetMapping(path = "{unitId}")
    public Unit getUnit(@PathVariable("unitId") Integer unitId) {
        return UNITS.stream().filter(unit -> unitId.equals(
                unit.getUnitId())).findFirst().orElseThrow(() -> new IllegalStateException("Unit does not exist " + unitId));
    }
}
