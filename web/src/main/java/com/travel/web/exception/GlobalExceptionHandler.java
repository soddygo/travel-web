package com.travel.web.exception;


import com.travel.pojo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理 Date : 5/23/18 Author : pengkai.fu
 *
 * @author soddy
 */
@ControllerAdvice(annotations = {RestController.class})
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 其他异常或非检查异常处理
     */
    @ExceptionHandler
    @ResponseBody
    public HttpResult exceptionHandler(Exception e) {
        log.error("Exception", e);
        return HttpResult.create("-1", e.getMessage());
    }

}
