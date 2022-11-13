package com.example.rentalcarbookingserver.vo.resp;

import java.util.List;

public class CarStockInfoResp {
    private List<CarStockInfo> cars;

    public List<CarStockInfo> getCars() {
        return cars;
    }

    public void setCars(List<CarStockInfo> cars) {
        this.cars = cars;
    }
}
