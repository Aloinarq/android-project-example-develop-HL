package com.levente.project_retrofit.api

import com.levente.project_retrofit.api.model.*
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

    @POST(BackendConstants.POST_CREATE_TASK)
    suspend fun createTask(@Body taskRequest : TaskRequestBody, @Header(BackendConstants.HEADER_TOKEN) token: String): Response<TaskRequestBody>

    @GET(BackendConstants.GET_ACTIVITIES_URL)
    suspend fun getActivities(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<ActivityResponse>>

    @POST(BackendConstants.POST_UPDATE_PROFILE)
    suspend fun updateProfile(@Body profileRequest : UpdateProfileRequestBody, @Header(BackendConstants.HEADER_TOKEN) token: String): Response<UpdateProfileRequestBody>

    @GET(BackendConstants.GET_DEPARTMENTS_URL)
    suspend fun getDepartments(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<DepartmentResponse>>

    @GET(BackendConstants.GET_USERS_URL)
    suspend fun getUsers(@Header(BackendConstants.HEADER_TOKEN) token: String): Response<List<SettingsResponse>>
}