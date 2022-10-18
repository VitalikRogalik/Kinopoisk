package com.example.kinopoisk.network

import retrofit2.Response
import java.lang.Exception

// Класс, созданный для обработки ошибок сетевого запроса. Тк использовать OnFailure в VM не удается
data class SimpleResponse<T>(       // Т параметризированый паараметр для типа
    val status: Status,
    val data: Response<T>?,         //nullable с учетом network failure
    val exception: Exception?       //nullable потому что в случае успеха exception не будет
){
    companion object{
        fun <T> success(data: Response<T>): SimpleResponse<T> {
            return SimpleResponse(
                status = Status.Success,
                data = data,
                exception = null
            )
        }

        fun <T> failure(exception: Exception): SimpleResponse<T> {
            return SimpleResponse(
                status = Status.Failure,
                data = null,
                exception = exception
            )
        }
    }

    sealed class Status{
        object Success: Status()
        object Failure: Status()
    }

    val failed: Boolean
        get() = this.status == Status.Failure

    val isSuccessful: Boolean
        get() = !failed && this.data?.isSuccessful ==true

    val body: T
        get() = this.data!!.body()!!
}