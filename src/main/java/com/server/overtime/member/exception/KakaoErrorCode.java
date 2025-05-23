package com.server.overtime.member.exception;

import com.server.overtime.common.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum KakaoErrorCode implements ErrorCode {
    INCORRECT_ID_CODE(HttpStatus.BAD_REQUEST, "KAKAO001", "카카오 idCode 값이 유효하지 않습니다."),
    KAKAO_SERVER_ERROR(HttpStatus.BAD_REQUEST, "KAKAO002", "카카오 서버 오류입니다."),
    INCORRECT_ACCESSTOKEN(HttpStatus.BAD_REQUEST, "KAKAO003", "카카오 엑세스 토큰이 올바르지 않습니다."),
    KAKAO_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "KAKAO004", "요청한 카카오 유저가 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private String message;

}
