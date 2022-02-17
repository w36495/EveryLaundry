package com.w36495.everylaundry.model.repository

import com.w36495.everylaundry.model.RetrofitBuilder
import com.w36495.everylaundry.model.network.LaundryService

class LaundryRepository {
    private val laundryService: LaundryService =
        RetrofitBuilder.getClient()!!.create(LaundryService::class.java)

    suspend fun getLaundryList() = laundryService.getLaundryList()
}