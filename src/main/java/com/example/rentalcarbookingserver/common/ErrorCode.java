package com.example.rentalcarbookingserver.common;

/**
 * 业务错误码
 */
public enum ErrorCode {

    INTERNAL_ERROR(2100, "Server internal error"),
    DATE_FORMAT_ERROR(2101, "Date not entered or the date format is incorrect, the correct format is(yyyy-MM-dd)"),
    DATE_CONFLICT_ERROR(2102, "Scheduled time slots conflict"),
    NO_CAR_MODEL_ERROR(2103, "No model number entered or no car for that model"),
    NO_CAR_STOCK_ERROR(2104, "This model of car is not in stock"),
    DATE_COMPARE_ERROR(2105, "The end date must be equal to or greater than the start date"),
    PARSE_JSON_ERROR(2106, "The JSON could not be parsed");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
