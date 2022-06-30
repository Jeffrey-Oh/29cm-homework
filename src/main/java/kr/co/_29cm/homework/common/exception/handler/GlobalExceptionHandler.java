package kr.co._29cm.homework.common.exception.handler;

import kr.co._29cm.homework.common.exception.BadRequestApiException;
import kr.co._29cm.homework.common.exception.CommonException;
import kr.co._29cm.homework.common.exception.NotFoundDataException;
import kr.co._29cm.homework.common.exception.message.ResponseCode;
import kr.co._29cm.homework.common.exception.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외 처리를 위한 공통 Exception 설정 및 AOP 핸들러 지정
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 200 And Fail
     * SoldOutException
     * NotFoundDataException
     */
    @ResponseBody
    @ExceptionHandler(CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse CommonException(CommonException e) {

        if (e.getResponseCode().equals(ResponseCode.VALIDATION)) {
            return ErrorResponse.fail(((BadRequestApiException) e).getField());
        } else if (e.getResponseCode().equals(ResponseCode.NOT_FOUND)) {
            return ErrorResponse.fail(((NotFoundDataException) e).getField(), e.getResponseCode().getDescription(), e.getResponseCode());
        }

        return ErrorResponse.fail(e.getResponseCode());
    }

    /**
     * Validation 실패 (RequestParam)
     * HttpStatus 400
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse MissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ErrorResponse.fail(e.getParameterName());
    }

    /**
     * 허용되지 않는 방법(Request Method - GET, POST, PUT, DELETE)
     * HttpStatus 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ErrorResponse.fail(ResponseCode.METHOD_NOT_SUPPORTED);
    }

    /**
     * Validation 실패 (RequestBody)
     * HttpStatus 417
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    public ErrorResponse MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String field = e.getBindingResult().getFieldError().getField();
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ErrorResponse.fail(field, message, ResponseCode.VALIDATION);
    }

}
