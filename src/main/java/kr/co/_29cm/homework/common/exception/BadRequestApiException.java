package kr.co._29cm.homework.common.exception;

import kr.co._29cm.homework.common.exception.message.ResponseCode;
import lombok.Getter;

@Getter
public class BadRequestApiException extends CommonException {

    private String field;

    public BadRequestApiException(String field) {
        super(ResponseCode.VALIDATION.getDescription(), ResponseCode.VALIDATION);
        this.field = field;
    }

    public BadRequestApiException(ResponseCode responseCode) {
        super(responseCode.getDescription(), responseCode);
    }

    public BadRequestApiException(String field, ResponseCode responseCode, String replaceMessage) {
        super(responseCode.getDescription().replace("message", replaceMessage), responseCode);
        this.field = field;
    }

}