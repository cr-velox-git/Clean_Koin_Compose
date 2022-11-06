package com.phoenix.dikoin.utils


/**
 * Resource.kt handle the api response status
 * **/
sealed class Resource<T>(val data: T? = null,val message: String? = null) {

    class Success<T>(data:T?): Resource<T>(data)

    /**
     * I'm passing data to the error response cause sometimes we even though we were unable
     * to fetch data from api, we still have data from the local data base like from RoomDB or chase data.
     * **/
    class Error<T>(message: String, data:T? = null): Resource<T>(data, message)

    class Loading<T>(val isLoading: Boolean): Resource<T>(null)

}