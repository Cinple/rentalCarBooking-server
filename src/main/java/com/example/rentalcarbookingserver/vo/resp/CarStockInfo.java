package com.example.rentalcarbookingserver.vo.resp;

public class CarStockInfo {
    private String carModel;

    private Integer inStock;

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
}
