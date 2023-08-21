package com.example.schoolinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolinfo.model.ResponseModel
import com.example.schoolinfo.model.ResponseModelItem
import com.example.schoolinfo.network.ApiResponse
import com.example.schoolinfo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    // ViewModel code here
    private val _response: MutableLiveData<ApiResponse<ResponseModel>> = MutableLiveData()
    val response: LiveData<ApiResponse<ResponseModel>> = _response

    fun getSchoolsList() = viewModelScope.launch {
        try {
            repository.getSchoolsList().collect { values ->
                _response.value = values
            }
        } catch (e: Exception){
            _response.value = e.localizedMessage?.let { ApiResponse.Error(it) }
        }
    }

    private val _navigateToDetail = MutableLiveData<ResponseModelItem?>()
    val navigateToDetail: LiveData<ResponseModelItem?>
        get() = _navigateToDetail

    fun onSchoolItemClicked(schoolItem: ResponseModelItem) {
        _navigateToDetail.value = schoolItem
    }

    fun onDetailNavigationComplete() {
        _navigateToDetail.value = null
    }
}