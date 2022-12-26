
import com.levente.project_retrofit.api.BackendConstants
import com.levente.project_retrofit.api.UserApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BackendConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Will not be initialized unless you use it!
     * It is initialized only once. Next time when you use it, you get the value from cache memory.
     */
    val USER_API_SERVICE: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}