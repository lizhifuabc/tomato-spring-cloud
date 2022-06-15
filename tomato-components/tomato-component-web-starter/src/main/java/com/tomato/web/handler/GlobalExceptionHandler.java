package com.tomato.web.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tomato.data.response.Response;
import com.tomato.data.response.ResponseCode;
import com.tomato.data.exception.AbstractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局系统异常处理
 *
 * @author lizhifu
 * @date 2022/5/26
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {AbstractException.class})
    public Response serviceException(AbstractException e) {
        log.warn("AbstractException:",e);
        return Response.buildFailure(e.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Response processException(ValidationException e) {
        log.warn("JsonProcessingException:",e);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonProcessingException.class)
    public Response handleJsonProcessingException(JsonProcessingException e) {
        log.warn("JsonProcessingException:",e);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), e.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("HttpMessageNotReadableException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response bindException(BindException ex) {
        log.warn("BindException:", ex);
        try {
            String msg = Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage();
            if (Objects.nonNull(msg)) {
                return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
            }
        } catch (Exception ee) {
            log.debug("获取异常描述失败", ee);
        }
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.")
        );
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        String msg = "参数：[" + ex.getName() + "]的传入值：[" + ex.getValue() +
                "]与预期的字段类型：[" + Objects.requireNonNull(ex.getRequiredType()).getName() + "]不匹配";
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response illegalStateException(IllegalStateException ex) {
        log.warn("IllegalStateException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("MissingServletRequestParameterException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response nullPointerException(NullPointerException ex) {
        log.warn("NullPointerException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response illegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgumentException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.warn("MissingServletRequestPartException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response servletException(ServletException ex) {
        log.warn("ServletException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());

    }

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response multipartException(MultipartException ex) {
        log.warn("MultipartException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    /**
     * jsr 规范中的验证异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response constraintViolationException(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), message);
    }

    /**
     * spring 封装的参数验证异常， 在controller中没有写result参数时，会进入
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

    /**
     * 其他异常
     *
     * @param ex 异常
     */
    @ExceptionHandler(Exception.class)
    public Response otherExceptionHandler(Exception ex) {
        log.warn("Exception:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }


    /**
     * 返回状态码:405
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }


//    @ExceptionHandler(PersistenceException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Response persistenceException(PersistenceException ex) {
//        log.warn("PersistenceException:", ex);
//        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
//    }
//
//    @ExceptionHandler(MyBatisSystemException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Response myBatisSystemException(MyBatisSystemException ex) {
//        log.warn("PersistenceException:", ex);
//        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
//    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response sqlException(SQLException ex) {
        log.warn("SQLException:", ex);
        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public Response dataIntegrityViolationException(DataIntegrityViolationException ex) {
//        log.warn("DataIntegrityViolationException:", ex);
//        return Response.buildFailure(ResponseCode.FAILURE.getCode(), ex.getMessage());
//    }
}
