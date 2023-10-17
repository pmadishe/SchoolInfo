package com.example.schoolinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolinfo.model.ResponseModelItem
import com.example.schoolinfo.repository.RemoteDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: RemoteDataSource) : ViewModel() {
    // ViewModel code here
    private var _response: MutableLiveData<List<ResponseModelItem>> = MutableLiveData()
    val response: LiveData<List<ResponseModelItem>>  = _response

    init {
       getSchoolsList()
    }

    private fun getSchoolsList() {
        viewModelScope.launch {
            val schoolsListResponse  = repository.getSchoolList()
            _response.value = schoolsListResponse
        }
    }

}