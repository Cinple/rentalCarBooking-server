package com.example.rentalcarbookingserver.controller;

import com.example.rentalcarbookingserver.common.BizException;
import com.example.rentalcarbookingserver.common.CarModel;
import com.example.rentalcarbookingserver.common.ErrorCode;
import com.example.rentalcarbookingserver.service.CarBookingService;
import com.example.rentalcarbookingserver.vo.req.CarBookingInfoReq;
import com.example.rentalcarbookingserver.vo.resp.CarBookingInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarRentalInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class CarBookingController {

    private final CarBookingService carBookingService;

    @Autowired
    public CarBookingController(CarBookingService carBookingService) {
        this.carBookingService = carBookingService;
    }

    /**
     * 查询车辆库存信息接口
     * @param carModel 车辆型号
     * @return
     */
    @GetMapping("/v1/cars")
    @ResponseBody
    public CarStockInfoResp getCarsStockInfo(@RequestParam(value = "carModel", required = false) String carModel) {
        if (Objects.nonNull(carModel) && CarModel.toStandardCarModel(carModel) == null) {
            throw new BizException(ErrorCode.NO_CAR_MODEL_ERROR);
        }
        return carBookingService.getCarsStockInfo(carModel);
    }

    /**
     * 查询车辆租赁信息接口
     * @return
     */
    @GetMapping("/v1/rentals")
    @ResponseBody
    public CarRentalInfoResp getRentalsInfo() {
        return carBookingService.getRentalsInfo();
    }

    /**
     * 预定车辆
     * @param carBookingInfoReq
     * @return
     */
    @PostMapping("/v1/rentals")
    @ResponseBody
    public CarBookingInfoResp bookingCar(@RequestBody CarBookingInfoReq carBookingInfoReq) {
        if (CarModel.toStandardCarModel(carBookingInfoReq.getCarModel()) == null) {
            throw new BizException(ErrorCode.NO_CAR_MODEL_ERROR);
        }
        return carBookingService.bookingCar(carBookingInfoReq);
    }
}
