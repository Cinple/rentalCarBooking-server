package com.example.rentalcarbookingserver.entity;

import com.example.rentalcarbookingserver.util.DateUtil;
import com.example.rentalcarbookingserver.vo.req.CarBookingInfoReq;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class RentalInfo {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalInfo that = (RentalInfo) o;
        return Objects.equals(carID, that.carID)
                && Objects.equals(carModel, that.carModel)
                && Objects.equals(startDate, that.startDate)
                && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carID, carModel, startDate, endDate);
    }

    public static RentalInfo valueOf(CarBookingInfoReq req) throws ParseException {
        RentalInfo rentalInfo = new RentalInfo();
        rentalInfo.setCarModel(req.getCarModel());
        rentalInfo.setStartDate(DateUtil.parseDateStr(req.getStartDate()));
        rentalInfo.setEndDate(DateUtil.parseDateStr(req.getEndDate()));
        return rentalInfo;
    }

    public static final Comparator<RentalInfo> START_DATE_COMPARATOR = new RentalStartDateComparator();
    public static final Comparator<RentalInfo> END_DATE_COMPARATOR = new RentalEndDateComparator();

    public static class RentalStartDateComparator implements Comparator<RentalInfo> {

        @Override
        public int compare(RentalInfo o1, RentalInfo o2) {
            return o1.getStartDate().compareTo(o2.getStartDate());
        }
    }

    public static class RentalEndDateComparator implements Comparator<RentalInfo> {
        @Override
        public int compare(RentalInfo o1, RentalInfo o2) {
            return o1.getEndDate().compareTo(o2.getEndDate());
        }
    }
}
