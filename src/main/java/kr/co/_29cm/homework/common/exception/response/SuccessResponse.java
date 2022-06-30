package kr.co._29cm.homework.common.exception.response;

import kr.co._29cm.homework.common.exception.message.ResponseCode;

/**
 * SuccessResponse는 각 API Response Class에서 상속받아 사용
 */
public class SuccessResponse {
    
    public int rt;
    public String rtMsg;
    
    public SuccessResponse() {
        this.rt = 200;
        this.rtMsg = ResponseCode.SUCCESS.getDescription();
    }

    @Override
    public String toString() {
        return "{"
            + "\"rt\":\"" + rt + "\""
            + ", \"rtMsg\":\"" + rtMsg + "\""
            + "}";
    }
}
