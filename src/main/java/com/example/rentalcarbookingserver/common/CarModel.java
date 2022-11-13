package com.example.rentalcarbookingserver.common;

import java.util.Objects;

/**
 * 车辆型号
 */
public enum CarModel {
    Toyota_Camry("Toyota Camry"),
    BMW_650("BMW 650");

    private final String carModel;

    CarModel(String carModel) {
        this.carModel = carModel;
    }

    @Override
    public String toString() {
        return carModel;
    }

    public static CarModel toStandardCarModel(String cm) {
        if (Objects.equals(cm, "Toyota Camry")) {
            return Toyota_Camry;
        } else if (Objects.equals(cm, "BMW 650")) {
            return BMW_650;
        } else {
            return null;
        }
    }
}
