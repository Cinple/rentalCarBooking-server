package com.example.rentalcarbookingserver.dao;

import com.example.rentalcarbookingserver.entity.RentalInfo;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 车辆租赁信息管理
 */
@Component
public class CarRentalsManager {

    private List<RentalInfo> rentalInfoList = new CopyOnWriteArrayList<>();

    private Map<Integer, List<RentalInfo>> rentalMap = new ConcurrentHashMap<>();

    public List<RentalInfo> getRentalInfoList() {
        return rentalInfoList;
    }

    public Map<Integer, List<RentalInfo>> getRentalMap() {
        return rentalMap;
    }

    public void saveRentalInfo(RentalInfo rentalInfo) {
        rentalInfoList.add(rentalInfo);
        List<RentalInfo> rentalList = rentalMap.get(rentalInfo.getCarID());
        if (rentalList == null) {
            rentalList = new ArrayList<>();
        }
        rentalList.add(rentalInfo);
        rentalMap.put(rentalInfo.getCarID(), rentalList);
    }

}
