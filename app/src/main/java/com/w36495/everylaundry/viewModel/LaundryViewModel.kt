package com.w36495.everylaundry.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.w36495.everylaundry.model.data.Laundry
import com.w36495.everylaundry.model.repository.LaundryRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main

class LaundryViewModel : ViewModel() {

    private var _laundryList = MutableLiveData<List<Laundry>>()
    private val laundryRepository = LaundryRepository()

    val laundryList: LiveData<List<Laundry>> = _laundryList

    fun getLaundryList() {
        viewModelScope.launch {
            val response = laundryRepository.getLaundryList()
            withContext(Main) {
                if (response.isSuccessful) {
                    _laundryList.value = response.body
                }
            }
        }
    }
}