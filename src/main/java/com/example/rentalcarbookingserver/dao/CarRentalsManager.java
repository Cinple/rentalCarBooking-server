package com.example.rentalcarbookingserver.dao;

import com.example.rentalcarbookingserver.entity.RentalInfo;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 车辆租赁信息管理
 */
@Component
public class CarRentalsManager {

    private List<RentalInfo> rentalInfoList = new ArrayList<>();

    private Map<Integer, List<RentalInfo>> rentalMap = new HashMap<>();


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
