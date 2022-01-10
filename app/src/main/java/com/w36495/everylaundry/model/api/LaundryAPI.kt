package com.w36495.everylaundry.model.api

import com.w36495.everylaundry.model.data.Laundry
import retrofit2.Call
import retrofit2.http.GET

/**
 * 세탁소 관련 API
 */
interface LaundryAPI {

    @GET("selectLaundry.php")
    fun getLaundryList(): Call<List<Laundry>>

}