package kr.co._29cm.homework.common.exception;

import kr.co._29cm.homework.common.exception.message.ResponseCode;
import lombok.Getter;

@Getter
public class NotFoundDataException extends CommonException {

    private String field;

    public NotFoundDataException(String field) {
        super(ResponseCode.NOT_FOUND.getDescription(), ResponseCode.NOT_FOUND);
        this.field = field;
    }

    public NotFoundDataException(ResponseCode responseCode) {
        super(responseCode.getDescription(), ResponseCode.NOT_FOUND);
    }

}