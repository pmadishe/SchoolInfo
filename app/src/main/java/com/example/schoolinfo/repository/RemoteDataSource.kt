package com.example.schoolinfo.repository

import com.example.schoolinfo.network.SchoolApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val schoolApiService: SchoolApiService) {
    suspend fun getSchoolList() = schoolApiService.getSchoolList()
    suspend fun getSchoolDetails(dbn: String) = schoolApiService.getSchoolDetails(dbn)
}