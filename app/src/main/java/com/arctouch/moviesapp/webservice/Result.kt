package com.arctouch.moviesapp.webservice

/**
 * Created by erikcruz on 11/12/18
 */
sealed class Result<out T>

data class Success<out T : Any>(val data: T) : Result<T>()

data class Failure(val error: Throwable?) : Result<Nothing>()
