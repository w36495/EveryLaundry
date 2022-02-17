package com.w36495.everylaundry.model

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        private const val SERVER_URL = "http://52.78.215.163/"
        private var retrofit: Retrofit? = null

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            }
            return retrofit
        }
    }
}