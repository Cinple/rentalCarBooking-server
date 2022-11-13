package com.example.rentalcarbookingserver.common;

/**
 * 业务错误码
 */
public enum ErrorCode {

    INTERNAL_ERROR(2100, "服务器内部错误"),
    DATE_FORMAT_ERROR(2101, "未输入日期或日期格式不正确，正确的格式为（yyyy-MM-dd）"),
    DATE_CONFLICT_ERROR(2102, "预定时间段冲突"),
    NO_CAR_MODEL_ERROR(2103, "没有输入型号或没有该型号的车辆"),
    NO_CAR_STOCK_ERROR(2104, "该型号的车辆无库存"),
    DATE_COMPARE_ERROR(2105, "结束日期必须等于或大于开始日期"),
    PARSE_JSON_ERROR(2106, "无法解析JSON参数");

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
