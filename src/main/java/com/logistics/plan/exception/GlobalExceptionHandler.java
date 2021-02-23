package com.logistics.plan.exception;


import com.logistics.plan.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常.
     *
     * @param e the e
     * @return R
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R exception(Exception e) {
        log.error("全局异常信息 ex={}", e.getMessage(), e);
        return R.failed(e);
    }

    /**
     * MyException
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public R handleUnauthorized(MyException e) {
        log.error("自定义异常信息 ex={}", e.getMessage());
        return R.restResult(e.getCode(), e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R validExceptionHandler(MethodArgumentNotValidException e)
    {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return R.failed(message);
    }
}
