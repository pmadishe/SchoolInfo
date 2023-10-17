package com.example.schoolinfo.repository

import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import com.example.schoolinfo.model.ResponseModelItem
import com.example.schoolinfo.network.SchoolApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val schoolApiService: SchoolApiService) {
    suspend fun getSchoolList(): List<ResponseModelItem> {
        return schoolApiService.getSchoolList()
    }

    suspend fun getSchoolDetails(): List<DetailFragmentResponseModelItem> {
        return schoolApiService.getSchoolDetails()
    }

}
