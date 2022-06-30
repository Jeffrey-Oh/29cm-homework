package kr.co._29cm.homework.common.exception;

import kr.co._29cm.homework.common.exception.message.ResponseCode;
import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

    private ResponseCode responseCode;

    public CommonException(String message, ResponseCode responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

}
