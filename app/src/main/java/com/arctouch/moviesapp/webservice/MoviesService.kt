package com.arctouch.moviesapp.webservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by erikcruz on 11/11/18
 */
class MoviesService : MoviesApi {

    private val retrofit: Retrofit

    init {
        retrofit = getRetrofitInstance()
    }

    override fun upcomingMovies(apiKey: String, page: Int): Call<MoviesResponse> {
        val service = retrofit.create(MoviesApi::class.java)
        return service.upcomingMovies(apiKey, page)
    }

    private fun getRetrofitInstance(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply { level = okhttp3.logging.HttpLoggingInterceptor.Level.BASIC }

        val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.themoviedb.org/3/movie/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit
    }
}