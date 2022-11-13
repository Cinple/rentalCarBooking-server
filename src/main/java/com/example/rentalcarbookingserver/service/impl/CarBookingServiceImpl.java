package com.example.rentalcarbookingserver.service.impl;

import com.example.rentalcarbookingserver.common.BizException;
import com.example.rentalcarbookingserver.common.CarModel;
import com.example.rentalcarbookingserver.common.ErrorCode;
import com.example.rentalcarbookingserver.dao.CarRentalsManager;
import com.example.rentalcarbookingserver.dao.CarStockManager;
import com.example.rentalcarbookingserver.entity.CarInfo;
import com.example.rentalcarbookingserver.entity.RentalInfo;
import com.example.rentalcarbookingserver.service.CarBookingService;
import com.example.rentalcarbookingserver.vo.req.CarBookingInfoReq;
import com.example.rentalcarbookingserver.vo.resp.CarBookingInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarRentalInfoResp;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfo;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfoResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class CarBookingServiceImpl implements CarBookingService {

    private CarStockManager carStockManager;

    private CarRentalsManager carRentalsManager;

    @Autowired
    public CarBookingServiceImpl(CarStockManager carStockManager, CarRentalsManager carRentalsManager) {
        this.carStockManager = carStockManager;
        this.carRentalsManager = carRentalsManager;
    }
    @Override
    public CarStockInfoResp getCarsStockInfo(String carModel) {
        List<CarStockInfo> carStockInfoList = carStockManager.calculateCarStock(carModel);
        CarStockInfoResp carStockInfoResp = new CarStockInfoResp();
        carStockInfoResp.setCars(carStockInfoList);
        return carStockInfoResp;
    }

    @Override
    public CarRentalInfoResp getRentalsInfo() {
        List<RentalInfo> rentalInfoList = carRentalsManager.getRentalInfoList();
        CarRentalInfoResp carRentalInfoResp = new CarRentalInfoResp();
        carRentalInfoResp.setRentals(rentalInfoList);
        return carRentalInfoResp;
    }

    @Override
    public CarBookingInfoResp bookingCar(CarBookingInfoReq carBookingInfoReq) {
        Map<CarModel, List<CarInfo>> carInfoMap = carStockManager.getCarInfoMap();
        List<CarInfo> carInfoList = carInfoMap.get(CarModel.toStandardCarModel(carBookingInfoReq.getCarModel()));
        if (carInfoList == null) {
            throw new BizException(ErrorCode.NO_CAR_STOCK_ERROR);
        }
        RentalInfo rentalInfo;
        try {
            rentalInfo = RentalInfo.valueOf(carBookingInfoReq);
        } catch (ParseException e) {
            throw new BizException(ErrorCode.DATE_FORMAT_ERROR);
        }
        //开始日期必须小于结束日期
        if (rentalInfo.getStartDate().compareTo(rentalInfo.getEndDate()) > 0) {
            throw new BizException(ErrorCode.DATE_COMPARE_ERROR);
        }
        for (CarInfo carInfo : carInfoList) {
            CarBookingInfoResp carBookingInfoResp = bookingCar(carInfo, rentalInfo);
            if (carBookingInfoResp != null) {
                return carBookingInfoResp;
            }
        }
        throw new BizException(ErrorCode.DATE_CONFLICT_ERROR);
    }

    /**
     *
     * @param carInfo
     * @return 预定失败返回null
     */
    private CarBookingInfoResp bookingCar(CarInfo carInfo, RentalInfo rentalInfo) {
        Map<Integer, List<RentalInfo>> rentalMap = carRentalsManager.getRentalMap();
        List<RentalInfo> rentalInfoList = rentalMap.get(carInfo.getCarID());
        if (rentalInfoList == null || rentalInfoList.size() == 0) {
            rentalInfo.setCarID(carInfo.getCarID());
            carRentalsManager.saveRentalInfo(rentalInfo);
            return CarBookingInfoResp.valueOf(rentalInfo);
        }
        TreeSet<RentalInfo> rentalStartSet = new TreeSet<>(RentalInfo.START_DATE_COMPARATOR);
        rentalStartSet.addAll(rentalInfoList);

        RentalInfo lowerStart = rentalStartSet.lower(rentalInfo);
        RentalInfo higherStart = rentalStartSet.higher(rentalInfo);
        //start1 start_current end1的情况
        if (lowerStart != null && lowerStart.getEndDate().compareTo(rentalInfo.getStartDate()) >= 0) {
            return null;
        }
        //start_current start1 end_current的情况
        if (higherStart != null && higherStart.getStartDate().compareTo(rentalInfo.getEndDate()) <= 0) {
            return null;
        }
        rentalInfo.setCarID(carInfo.getCarID());
        carRentalsManager.saveRentalInfo(rentalInfo);
        return CarBookingInfoResp.valueOf(rentalInfo);
    }
}
