package com.example.rentalcarbookingserver.vo.resp;

import com.example.rentalcarbookingserver.entity.RentalInfo;

import java.util.List;

public class CarRentalInfoResp {
    private List<RentalInfo> rentals;

    public List<RentalInfo> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalInfo> rentals) {
        this.rentals = rentals;
    }
}
