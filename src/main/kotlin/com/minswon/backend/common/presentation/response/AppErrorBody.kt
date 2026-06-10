package com.minswon.backend.common.presentation.response

data class AppErrorBody(
    val code: String,
    val message: String,
    val details: List<AppErrorDetail>? = null,
) {

    constructor(type: AppErrorType) : this(
        code = type.name,
        message = type.message,
    )

    constructor(type: AppErrorType, details: List<AppErrorDetail>) : this(
        code = type.name,
        message = type.message,
        details = details,
    )
}

data class AppErrorDetail(
    val field: String,
    val message: String,
)
