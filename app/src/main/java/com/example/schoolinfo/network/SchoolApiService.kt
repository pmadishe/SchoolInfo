package com.example.schoolinfo.network

import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import com.example.schoolinfo.model.ResponseModelItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolApiService {

    @GET("resource/s3k6-pzi2.json")
     suspend fun getSchoolList(): List<ResponseModelItem>
    @GET("resource/f9bf-2cp4.json")
     suspend fun getSchoolDetails(): List<DetailFragmentResponseModelItem>

}


