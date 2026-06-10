package com.minswon.backend.common.presentation.response

data class AppResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val error: AppErrorBody? = null,
) {

    companion object {
        fun <T> success(data: T): AppResponse<T> {
            return AppResponse(
                success = true,
                data = data,
                error = null
            )
        }

        fun error(error: AppErrorType): AppResponse<Unit> {
            return AppResponse(
                success = false,
                data = null,
                error = AppErrorBody(error)
            )
        }

        fun error(error: AppErrorType, details: List<AppErrorDetail>): AppResponse<Unit> {
            return AppResponse(
                success = false,
                data = null,
                error = AppErrorBody(error, details)
            )
        }
    }
}
