package com.w36495.everylaundry.model.data

import com.google.gson.annotations.SerializedName

/**
 * 세탁소 데이터 클래스
 */
data class Laundry(

    @SerializedName("id")
    val laundryId: String,

    @SerializedName("name")
    val laundryName: String,

    @SerializedName("tel")
    val laundryTel: String,

    @SerializedName("address")
    val laundryAddress: String,

    @SerializedName("zip_code")
    val laundryZipCode: String,

    @SerializedName("type")
    val laundryType: Char,

    @SerializedName("coords_x")
    val laundryCoordsX: Double,

    @SerializedName("coords_y")
    val laundryCoordsY: Double
)

