package com.recyclerview.mynews

import android.telecom.Call
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

const val BASE_URL = "https://newsapi.org/v2/"
const val API_KEY = "51950e253923487e9b7c06fdc43e2e91"

interface NewsApi {
    @GET("top-headlines?apiKey=$API_KEY")

    fun getHeadlines(
        @Query("country") country: String, @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    )
            : retrofit2.Call<MainNews>

    @GET("top-headlines?apiKey=$API_KEY")

    fun getCategoryHeadlines(
        @Query("country") country: String,
        @Query("category") category : String ,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    )
            : retrofit2.Call<MainNews>

    object newsService {
        val newsInstance: NewsApi

        init {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            newsInstance = retrofit.create(NewsApi::class.java)
        }
    }

}