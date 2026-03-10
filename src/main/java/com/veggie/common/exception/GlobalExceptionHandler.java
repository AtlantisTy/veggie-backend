package com.veggie.common.exception;

import com.veggie.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("业务异常：{}, 请求路径：{}", e.getMessage(), request.getRequestURI());
        return Result.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e, HttpServletRequest request) {
        log.error("参数绑定异常：{}, 请求路径：{}", e.getMessage(), request.getRequestURI());
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return Result.error(400, "参数错误：" + message);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest request) {
        log.error("参数异常：{}, 请求路径：{}", e.getMessage(), request.getRequestURI());
        return Result.error(400, e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result<String> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        log.error("空指针异常：{}, 请求路径：{}", e.getMessage(), request.getRequestURI());
        return Result.error(500, "系统内部错误");
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e, HttpServletRequest request) {
        log.error("系统异常：{}, 请求路径：{}", e.getMessage(), request.getRequestURI(), e);
        return Result.error(500, "系统繁忙，请稍后重试");
    }
}
