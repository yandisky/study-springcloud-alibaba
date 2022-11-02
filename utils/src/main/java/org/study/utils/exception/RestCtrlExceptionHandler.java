package org.study.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.study.utils.constants.HttpCode;
import org.study.utils.resp.Result;

@RestControllerAdvice
public class RestCtrlExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(RestCtrlExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        logger.error("服务器异常：{}", e);
        return new Result<>(HttpCode.FAILURE, "服务器异常", e.getMessage());
    }
}
