package com.example.rentalcarbookingserver.controller;

import com.example.rentalcarbookingserver.common.BizException;
import com.example.rentalcarbookingserver.common.ErrorCode;
import com.example.rentalcarbookingserver.common.ErrorResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */

@ControllerAdvice
public class GlobalAdviceController {

    private static final Log logger = LogFactory.getLog(GlobalAdviceController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse defaultExceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("failed to handler request" + req, e);
        return ErrorResponse.globalInternalErrorResp();
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ErrorResponse bizExceptionHandler(HttpServletRequest req, BizException e) {
        logger.error("failed to handler request" + req, e);
        return ErrorResponse.globalBizErrorResp(e.getErrorCode());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResponse httpExceptionHandler(HttpServletRequest req, HttpMessageNotReadableException e) {
        logger.error("failed to handler request" + req, e);
        return ErrorResponse.globalBizErrorResp(ErrorCode.PARSE_JSON_ERROR);
    }
}
