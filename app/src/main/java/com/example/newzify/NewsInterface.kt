package com.example.newzify

import com.example.newzify.data.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/top-headlines?country=in&apiKey=234733e912204c4aad892271bff273bd
const val Base_Url="https://newsapi.org/"
const val ApiKey="234733e912204c4aad892271bff273bd"


interface NewsInterface {
    @GET("https://newsapi.org/v2/top-headlines?apiKey=$ApiKey")
    fun getHeadlines(@Query("country") country:String,@Query("page")page :Int):Call<News>
}

object NewsService{
    val NewsInstance:NewsInterface
    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        NewsInstance=retrofit.create(NewsInterface::class.java)

    }

}