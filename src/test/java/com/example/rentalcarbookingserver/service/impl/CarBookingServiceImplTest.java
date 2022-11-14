package com.example.rentalcarbookingserver.service.impl;

import com.example.rentalcarbookingserver.common.BizException;
import com.example.rentalcarbookingserver.entity.RentalInfo;
import com.example.rentalcarbookingserver.service.CarBookingService;
import com.example.rentalcarbookingserver.vo.req.CarBookingInfoReq;
import com.example.rentalcarbookingserver.vo.resp.CarBookingInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarRentalInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfo;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfoResp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CarBookingServiceImplTest {

    @Autowired
    private CarBookingService carBookingService;

    /**
     * 测试查询车辆库存
     */
    @Test
    public void getCarsStockInfoWithNoCarModel() {
        CarStockInfoResp carsStockInfo = carBookingService.getCarsStockInfo(null);
        Assert.assertNotNull(carsStockInfo);
        List<CarStockInfo> cars = carsStockInfo.getCars();
        Assert.assertEquals(2, cars.size());
    }

    /**
     * 测试查询指定型号车辆库存
     */
    @Test
    public void getCarsStockInfoWithCarModel() {
        String carModel = "BMW 650";
        CarStockInfoResp carsStockInfo = carBookingService.getCarsStockInfo(carModel);
        Assert.assertNotNull(carsStockInfo);
        List<CarStockInfo> cars = carsStockInfo.getCars();
        Assert.assertEquals(1, cars.size());
        CarStockInfo carStockInfo = cars.get(0);
        Assert.assertEquals(carStockInfo.getCarModel(), carModel);
        Assert.assertEquals(2, carStockInfo.getInStock().longValue());
    }


    /**
     * 测试查询获取车辆租赁信息
     */
    @Test
    public void getRentalsInfo() {
        CarBookingInfoReq carBookingInfoReq = new CarBookingInfoReq();
        carBookingInfoReq.setCarModel("BMW 650");
        carBookingInfoReq.setStartDate("2020-03-31");
        carBookingInfoReq.setEndDate("2020-03-31");
        carBookingService.bookingCar(carBookingInfoReq);

        CarRentalInfoResp rentalsInfo = carBookingService.getRentalsInfo();
        Assert.assertNotNull(rentalsInfo);
        List<RentalInfo> rentals = rentalsInfo.getRentals();
        Assert.assertTrue(rentals.size() > 0);
        RentalInfo rentalInfo = rentals.get(0);
        Assert.assertNotNull(rentalInfo.getCarID());
    }

    /**
     * 测试预定车辆
     */
    @Test
    public void bookingCar() {
        CarBookingInfoReq carBookingInfoReq = new CarBookingInfoReq();
        carBookingInfoReq.setCarModel("BMW 650");
        carBookingInfoReq.setStartDate("2020-03-31");
        carBookingInfoReq.setEndDate("2020-03-31");
        CarBookingInfoResp carBookingInfoResp = carBookingService.bookingCar(carBookingInfoReq);
        Assert.assertNotNull(carBookingInfoResp);

        Integer carID = carBookingInfoResp.getCarID();
        Assert.assertNotNull(carID);
        Assert.assertEquals(carBookingInfoResp.getCarModel(), "BMW 650");
    }

    /**
     * 测试预定车辆,并输入错误的日期格式
     */
    @Test(expected = BizException.class)
    public void bookingCarWithErrorDateFormat() {
        CarBookingInfoReq carBookingInfoReq = new CarBookingInfoReq();
        carBookingInfoReq.setCarModel("BMW 650");
        carBookingInfoReq.setStartDate("2020/03/31");
        carBookingInfoReq.setEndDate("2020/03/31");
        CarBookingInfoResp carBookingInfoResp = carBookingService.bookingCar(carBookingInfoReq);
    }

    /**
     * 测试预定车辆,并选择同一时间
     */
    @Test(expected = BizException.class)
    public void bookingCarWithConflictDate() {
        for (int i = 0; i < 3; i++) {//该车型有两辆车，故测试预定3次
            CarBookingInfoReq carBookingInfoReq = new CarBookingInfoReq();
            carBookingInfoReq.setCarModel("BMW 650");
            carBookingInfoReq.setStartDate("2020/03/31");
            carBookingInfoReq.setEndDate("2020/03/31");
            CarBookingInfoResp carBookingInfoResp = carBookingService.bookingCar(carBookingInfoReq);
        }
    }
}