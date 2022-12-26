package com.levente.project_retrofit.api

import com.levente.project_retrofit.api.model.LoginRequestBody
import com.levente.project_retrofit.api.model.LoginResponse
import com.levente.project_retrofit.api.model.SettingsResponse
import com.levente.project_retrofit.api.model.TaskResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApiService {

    @POST(BackendConstants.LOGIN_URL)
    suspend fun login(@Body loginRequest: LoginRequestBody): Response<LoginResponse>

    @GET(BackendConstants.GET_TASKS_URL)
    suspend fun getTasks(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<TaskResponse>>

    @GET(BackendConstants.GET_SETTINGS_URL)
    suspend fun getSettings(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<SettingsResponse>
}