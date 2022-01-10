package com.w36495.everylaundry

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        private const val SERVER_URL = "http://52.78.215.163/"
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            }

            return retrofit
        }

    }

}