package com.example.rentalcarbookingserver.common;

/**
 * 错误信息响应
 */
public class ErrorResponse {

    private ErrorBinary error;

    public ErrorResponse(ErrorBinary error) {
        this.error = error;
    }

    public ErrorBinary getError() {
        return error;
    }

    public void setError(ErrorBinary error) {
        this.error = error;
    }

    public static ErrorResponse globalInternalErrorResp() {
        return new ErrorResponse(new ErrorBinary(ErrorCode.INTERNAL_ERROR));
    }

    public static ErrorResponse globalBizErrorResp(ErrorCode errorCode) {
        return new ErrorResponse(new ErrorBinary(errorCode));
    }

    public static class ErrorBinary {
        private int code;
        private String message;

        public ErrorBinary(ErrorCode errorCode) {
            this.code = errorCode.getCode();
            this.message = errorCode.getMessage();
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
}
