package com.oksusu.susu.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus, val description: String) {
    /** Common Error Code */
    BAD_REQUEST_ERROR(HttpStatus.BAD_REQUEST, "bad request"),
    INVALID_INPUT_VALUE_ERROR(HttpStatus.BAD_REQUEST, "input is invalid value"),
    INVALID_TYPE_VALUE_ERROR(HttpStatus.BAD_REQUEST, "invalid type value"),
    METHOD_NOT_ALLOWED_ERROR(HttpStatus.METHOD_NOT_ALLOWED, "Method type is invalid"),
    INVALID_MEDIA_TYPE_ERROR(HttpStatus.BAD_REQUEST, "invalid media type"),
    QUERY_DSL_NOT_EXISTS_ERROR(HttpStatus.NOT_FOUND, "not found query dsl"),
    COROUTINE_CANCELLATION_ERROR(HttpStatus.BAD_REQUEST, "coroutine cancellation error"),

    /** Auth Error Code */
    FAIL_TO_VERIFY_TOKEN_ERROR(HttpStatus.BAD_REQUEST, "fail to verify token"),
    NOT_ACCESS_TOKEN(HttpStatus.BAD_REQUEST, "엑세스 토큰이 아닙니다."),
    NOT_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 아닙니다."),

    /** User Error Code */
    USER_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "유저 정보를 찾을 수 없습니다."),
    ALREADY_REGISTERED_USER(HttpStatus.NOT_FOUND, "이미 가입된 유저입니다."),

    /** Ledger Error Code */
    LEDGER_INVALID_DUE_DATE_ERROR(HttpStatus.BAD_REQUEST, "잘못된 일정 등록 요청입니다."),
    FAIL_TO_CREATE_LEDGER_ERROR(HttpStatus.BAD_REQUEST, "장부 생성을 실패했습니다."),

    /** Category Error Code */
    NOT_FOUND_CATEGORY_ERROR(HttpStatus.NOT_FOUND, "카테고리 정보를 찾을 수 없습니다."),

    /** Relationship Error Code */
    NOT_FOUND_RELATIONSHIP_ERROR(HttpStatus.NOT_FOUND, "관계 정보를 찾을 수 없습니다."),
    ;
}
