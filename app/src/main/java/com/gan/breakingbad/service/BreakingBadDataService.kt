package com.gan.breakingbad.service

import com.gan.breakingbad.model.GetCharacters
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface BreakingBadDataService {

    @GET("/api/characters")
    fun getCharacters(): Observable<List<GetCharacters>>


    companion object {
        fun create(): BreakingBadDataService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.breakingbadapi.com")
                .client(client)
                .build()

            return retrofit.create(BreakingBadDataService::class.java)
        }
    }

}