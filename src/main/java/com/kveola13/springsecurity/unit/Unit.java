package com.kveola13.springsecurity.unit;

public class Unit {
    private final Integer unitId;
    private final String unitName;

    public Unit(Integer unitId, String unitName) {
        this.unitId = unitId;
        this.unitName = unitName;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                '}';
    }
}
