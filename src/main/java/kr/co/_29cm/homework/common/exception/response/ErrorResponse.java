package kr.co._29cm.homework.common.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.co._29cm.homework.common.exception.message.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String field;
    private String errorMessage;
    private ResponseCode responseCode;

    public static ErrorResponse fail(String field) {
        return ErrorResponse.builder()
            .field(field)
            .errorMessage(ResponseCode.VALIDATION.getDescription())
            .responseCode(ResponseCode.VALIDATION)
            .build();
    }

    public static ErrorResponse fail(String field, String errorMessage, ResponseCode responseCode) {
        return ErrorResponse.builder()
            .field(field)
            .errorMessage(errorMessage)
            .responseCode(responseCode)
            .build();
    }

    public static ErrorResponse fail(ResponseCode errorResponseCode) {
        return ErrorResponse.builder()
            .errorMessage(errorResponseCode.getDescription())
            .responseCode(errorResponseCode)
            .build();
    }

}
