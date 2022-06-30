package kr.co._29cm.homework.common.exception.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    SUCCESS("성공"),

    METHOD_NOT_SUPPORTED("올바르지 않은 메소드 타입입니다."),
    VALIDATION("파라미터가 올바르지 않습니다."),
    NOT_FOUND("조회된 정보가 없습니다."),
    SOLD_OUT("재고가 없습니다.");

    private final String description;

}
