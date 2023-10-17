package com.example.schoolinfo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import com.example.schoolinfo.repository.RemoteDataSource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(private val repository: RemoteDataSource) :
    ViewModel() {

    // ViewModel code here
    private val _response: MutableLiveData<List<DetailFragmentResponseModelItem>> = MutableLiveData()
    private val _responseSchool: MutableLiveData<DetailFragmentResponseModelItem> = MutableLiveData()
    val responseSchool: LiveData<DetailFragmentResponseModelItem> = _responseSchool
    val response: LiveData<List<DetailFragmentResponseModelItem>> = _response

    fun getSchoolDetails() {
        viewModelScope.launch {
            val schoolDetailsRes = repository.getSchoolDetails()

            _response.value = schoolDetailsRes

//            for (school in schoolDetailsRes) {
//                _responseSchool.value = repository.getSchoolData(school.dbn)
//            }
        }
    }
}