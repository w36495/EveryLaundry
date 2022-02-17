package com.w36495.everylaundry.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.w36495.everylaundry.model.data.Laundry

/**
 * 세탁소 관련 API DTO
 */
data class LaundryListResponse(
    @SerializedName("isSuccessful")
    val isSuccessful: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("body")
    @Expose
    val body: List<Laundry>
)
