package com.levente.project_retrofit.api

import com.levente.project_retrofit.api.model.*
import retrofit2.Response

class ThreeTrackerRepository {

    /**
     * 'suspend' keyword means that this function can be blocking.
     * We need to be aware that we can only call them from within a coroutine or an other suspend function!
     */
    suspend fun login(loginRequestBody: LoginRequestBody): Response<LoginResponse> {
        return RetrofitInstance.USER_API_SERVICE.login(loginRequestBody)
    }

    suspend fun getTasks(token: String): Response<List<TaskResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getTasks(token)
    }

    suspend fun getSettings(token: String): Response<SettingsResponse> {
        return RetrofitInstance.USER_API_SERVICE.getSettings(token)
    }

    suspend fun createTask(taskRequestBody: TaskRequestBody, token: String): Response<TaskRequestBody> {
        return RetrofitInstance.USER_API_SERVICE.createTask(taskRequestBody, token)
    }

    suspend fun getActivities(token: String): Response<List<ActivityResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getActivities(token)
    }

    suspend fun updateProfile(profileRequestBody: UpdateProfileRequestBody, token: String): Response<UpdateProfileRequestBody>{
        return RetrofitInstance.USER_API_SERVICE.updateProfile(profileRequestBody,token)
    }

    suspend fun getDepartments(token: String): Response<List<DepartmentResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getDepartments(token)
    }

    suspend fun getUsers(token: String): Response<List<SettingsResponse>> {
        return RetrofitInstance.USER_API_SERVICE.getUsers(token)
    }
}