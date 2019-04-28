package com.android.varnit.grabassignment.api

import com.android.varnit.grabassignment.models.NewsModel
import com.android.varnit.grabassignment.utils.Constants
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by varnit.khandelwal on 28/04/2019.
 */
interface ApiServiceInterface {

    @GET("v2/everything")
    fun getNewsList(@Query("q") query: String, @Query("apiKey") apiKey: String): Observable<NewsModel>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val retrofit = retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.NEWS_BASE_URL)
                .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}