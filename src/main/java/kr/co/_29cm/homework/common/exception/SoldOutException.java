package kr.co._29cm.homework.common.exception;

import kr.co._29cm.homework.common.exception.message.ResponseCode;
import lombok.Getter;

@Getter
public class SoldOutException extends CommonException {

    public SoldOutException() {
        super(ResponseCode.SOLD_OUT.getDescription(), ResponseCode.SOLD_OUT);
    }

}
