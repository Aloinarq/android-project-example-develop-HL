package com.levente.project_retrofit.api

import com.levente.project_retrofit.api.model.LoginRequestBody
import com.levente.project_retrofit.api.model.LoginResponse
import com.levente.project_retrofit.api.model.SettingsResponse
import com.levente.project_retrofit.api.model.TaskResponse
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
}