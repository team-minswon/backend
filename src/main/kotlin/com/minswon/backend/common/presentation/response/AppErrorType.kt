package com.minswon.backend.common.presentation.response

import org.springframework.http.HttpStatus

enum class AppErrorType(
    val status: HttpStatus,
    val message: String,
) {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내 오류가 발생했습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리소스입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
}
