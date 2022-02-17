package com.w36495.everylaundry.model.network

import com.w36495.everylaundry.model.response.LaundryListResponse
import retrofit2.http.GET

/**
 * 세탁소 관련 API
 */
interface LaundryService {

    @GET("/Laundry/selectLaundryList.php")
    suspend fun getLaundryList(): LaundryListResponse

}