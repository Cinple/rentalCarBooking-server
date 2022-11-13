package com.example.rentalcarbookingserver.vo.resp;

import com.example.rentalcarbookingserver.entity.RentalInfo;
import com.example.rentalcarbookingserver.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CarBookingInfoResp {
    private Integer carID;
    private String carModel;
    @JsonFormat(pattern = DateUtil.FORMAT, timezone = "GMT")
    private Date startDate;
    @JsonFormat(pattern = DateUtil.FORMAT, timezone = "GMT")
    private Date endDate;

    public Integer getCarID() {
        return carID;
    }

    public void setCarID(Integer carID) {
        this.carID = carID;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public static CarBookingInfoResp valueOf(RentalInfo rentalInfo) {
        CarBookingInfoResp carBookingInfoResp = new CarBookingInfoResp();
        carBookingInfoResp.setCarID(rentalInfo.getCarID());
        carBookingInfoResp.setCarModel(rentalInfo.getCarModel());
        carBookingInfoResp.setStartDate(rentalInfo.getStartDate());
        carBookingInfoResp.setEndDate(rentalInfo.getEndDate());
        return carBookingInfoResp;
    }
}
