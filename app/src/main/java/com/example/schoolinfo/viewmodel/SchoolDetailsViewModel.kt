package com.example.schoolinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolinfo.model.DetailFragmentResponseModel
import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import com.example.schoolinfo.model.ResponseModel
import com.example.schoolinfo.network.ApiResponse
import com.example.schoolinfo.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
        // ViewModel code here
        private val _response: MutableLiveData<ApiResponse<List<DetailFragmentResponseModelItem>>> = MutableLiveData()
        val response: LiveData<ApiResponse<List<DetailFragmentResponseModelItem>>> = _response

        fun getSchoolDetails(dbn: String) = viewModelScope.launch {
            try {
                repository.getSchoolDetails(dbn).collect { values ->
                    _response.value = values
                }
            } catch (e: Exception){
                _response.value = e.localizedMessage?.let { ApiResponse.Error(it) }
            }
        }

    fun getSchoolDetailsFlow(dbn: String): Flow<ApiResponse<List<DetailFragmentResponseModelItem>>> = flow {
        try {
            repository.getSchoolDetails(dbn).collect { values ->
                emit(values)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.localizedMessage ?: "An error occurred"))
        }
    }

}
