package com.example.rentalcarbookingserver.entity;

import com.example.rentalcarbookingserver.common.CarModel;

/**
 * 车辆信息
 */
public class CarInfo {

    private Integer carID;
    private CarModel carModel;

    public CarInfo(Integer carID, CarModel carModel) {
        this.carID = carID;
        this.carModel = carModel;
    }

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
