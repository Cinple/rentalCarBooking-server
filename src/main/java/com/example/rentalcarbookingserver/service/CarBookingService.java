package com.example.rentalcarbookingserver.service;

import com.example.rentalcarbookingserver.vo.req.CarBookingInfoReq;
import com.example.rentalcarbookingserver.vo.resp.CarBookingInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarRentalInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfoResp;

public interface CarBookingService {

    /**
     * 获取车辆库存信息
     * @param carModel
     * @return
     */
    CarStockInfoResp getCarsStockInfo(String carModel);

    /**
     * 获取车辆租赁信息
     * @return
     */
    CarRentalInfoResp getRentalsInfo();

    /**
     * 预定车辆
     * @param carBookingInfoReq
     * @return
     */
    CarBookingInfoResp bookingCar(CarBookingInfoReq carBookingInfoReq);
}
