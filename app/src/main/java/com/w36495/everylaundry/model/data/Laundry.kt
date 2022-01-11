package com.w36495.everylaundry.model.data

import com.google.gson.annotations.SerializedName

/**
 * 세탁소 데이터 클래스
 */
data class Laundry(

    @SerializedName("LAUNDRY_KEY")
    val laundryKey: Int,

    @SerializedName("LAUNDRY_NM")
    val laundryName: String,

    @SerializedName("LAUNDRY_TEL")
    val laundryTel: String,

    @SerializedName("LAUNDRY_ADDR")
    val laundryAddress: String,

    @SerializedName("LAUNDRY_ZIP_CODE")
    val laundryZipCode: String,

    @SerializedName("LAUNDRY_TYPE")
    val laundryType: Char,

    @SerializedName("COORDS_X")
    val laundryCoordsX: Double,

    @SerializedName("COORDS_Y")
    val laundryCoordsY: Double
)

data class JsonLaundry(
    val laundryList: String
)
