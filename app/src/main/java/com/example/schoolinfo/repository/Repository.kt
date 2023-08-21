package com.example.schoolinfo.repository

import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import com.example.schoolinfo.model.ResponseModel
import com.example.schoolinfo.network.AbstractedApiResponse
import com.example.schoolinfo.network.ApiResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : AbstractedApiResponse() {

    suspend fun getSchoolsList(): Flow<ApiResponse<ResponseModel>> {
        return flow<ApiResponse<ResponseModel>> {
            emit(doSafeApiCall { remoteDataSource.getSchoolList()})
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSchoolDetails(dbn: String): Flow<ApiResponse<List<DetailFragmentResponseModelItem>>> {
        return flow<ApiResponse<List<DetailFragmentResponseModelItem>>> {
            emit(doSafeApiCall {remoteDataSource.getSchoolDetails(dbn)})
        }.flowOn(Dispatchers.IO)
    }

}