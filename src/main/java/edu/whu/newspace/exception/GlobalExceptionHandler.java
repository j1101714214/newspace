package edu.whu.newspace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author Newspace
 * @version 1.0
 * @description 全局异常控制其
 * @date 2023/5/23 11:12
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TouTiaoException.class)
    public ResponseEntity<ErrorResponse> touTiaoExceptionhandler(TouTiaoException exception) {
        String errMsg = exception.getErrMessage();
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationExceptionhandler(MethodArgumentNotValidException exception) {
        String errMsg = exception.getBindingResult().getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> validationExceptionhandler(BindException exception) {
        String errMsg = exception.getAllErrors().stream()
                .map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> validationExceptionhandler(ConstraintViolationException exception) {
        String errMsg = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionhandler(Exception exception) {
        String errMsg = exception.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(errMsg);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
