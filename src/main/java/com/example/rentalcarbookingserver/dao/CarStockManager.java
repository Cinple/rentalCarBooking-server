package com.example.rentalcarbookingserver.dao;

import com.example.rentalcarbookingserver.common.CarModel;
import com.example.rentalcarbookingserver.entity.CarInfo;
import com.example.rentalcarbookingserver.vo.resp.CarStockInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 管理车辆库存
 */
@Component
public class CarStockManager implements InitializingBean {

    private final List<CarInfo> carInfoList = new ArrayList<>();

    private final Map<CarModel, List<CarInfo>> carInfoMap = new HashMap<>();

    public List<CarInfo> getCarInfoList() {
        return carInfoList;
    }

    public Map<CarModel, List<CarInfo>> getCarInfoMap() {
        return carInfoMap;
    }

    public List<CarStockInfo> calculateCarStock(String carModel) {
        Map<CarModel, Integer> stockMap = new HashMap<>();
        for (CarInfo carInfo : carInfoList) {
            stockMap.merge(carInfo.getCarModel(), 1, Integer::sum);
        }
        List<CarStockInfo> infoList = new ArrayList<>();
        for (Map.Entry<CarModel, Integer> entry : stockMap.entrySet()) {
            CarStockInfo carStockInfo = new CarStockInfo();
            carStockInfo.setCarModel(entry.getKey().toString());
            carStockInfo.setInStock(entry.getValue());
            if (Objects.isNull(carModel) || Objects.equals(carModel, entry.getKey().toString())) {
                infoList.add(carStockInfo);
            }
        }
        return infoList;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initCarInfo(carInfoList);
        for (CarInfo carInfo : carInfoList) {
            List<CarInfo> carList = carInfoMap.get(carInfo.getCarModel());
            if (carList == null) {
                carList = new ArrayList<>();
            }
            carList.add(carInfo);
            carInfoMap.put(carInfo.getCarModel(), carList);
        }
    }

    /**
     * 初始化车辆库存信息
     */
    public static void initCarInfo(List<CarInfo> carInfoList) {
        carInfoList.add(new CarInfo(1, CarModel.Toyota_Camry));
        carInfoList.add(new CarInfo(2, CarModel.Toyota_Camry));
        carInfoList.add(new CarInfo(3, CarModel.BMW_650));
        carInfoList.add(new CarInfo(4, CarModel.BMW_650));
    }
}
