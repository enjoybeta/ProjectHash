package hash.application.managers

import android.util.Log
import com.google.gson.Gson
import hash.application.dataType.NewUser
import hash.application.dataType.SearchCoarse
import hash.application.dataType.SearchPrecise
import hash.application.dataType.User
import okhttp3.*

/**
 * Created by gouji on 3/18/2018.
 */
//use Singleton by object
object WebManager {
    private const val serverAddress: String = "https://www.enjoybeta.com"
    private val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")

    private val client: OkHttpClient = OkHttpClient()

    //validate conenction to server
    fun proofConnection(): Boolean {
        try {
            val request = Request.Builder()
                    .url("$serverAddress/")
                    .build()
            val response = client.newCall(request).execute()
            if (response.body()!!.string() != "HASH is Online") {
                return false
            }
        } catch (e: Exception) {
            Log.e("log_WebManager", e.stackTrace.toString())
            return false
        }
        return true
    }

    // first recipe suggestion for today
    fun getToday1(): String {
        val request = Request.Builder()
                .url("$serverAddress/today1")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // second recipe suggestion for today
    fun getToday2(): String {
        val request = Request.Builder()
                .url("$serverAddress/today2")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // third recipe suggestion for today
    fun getToday3(): String {
        val request = Request.Builder()
                .url("$serverAddress/today3")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // fourth recipe suggestion for today
    fun getToday4(): String {
        val request = Request.Builder()
                .url("$serverAddress/today4")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // search with keywords
    fun searchPrecise(input: SearchPrecise): String {
        val jsonStr: String = Gson().toJson(input)
        val body = RequestBody.create(JSON, jsonStr)
        val request = Request.Builder()
                .url("$serverAddress/searchPrecise")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // search with conditions (num people, ingredients choice)
    fun searchCoarse(input: SearchCoarse): String {
        val jsonStr: String = Gson().toJson(input)
        val body = RequestBody.create(JSON, jsonStr)
        val request = Request.Builder()
                .url("$serverAddress/searchCoarse")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // user signup: check if username is valid
    fun userSignup(input: NewUser): String {
        val jsonStr: String = Gson().toJson(input)
        val body = RequestBody.create(JSON, jsonStr)
        val request = Request.Builder()
                .url("$serverAddress/signup")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // user login: check if user is valid
    fun userLogin(input: User): String {
        val jsonStr: String = Gson().toJson(input)
        val body = RequestBody.create(JSON, jsonStr)
        val request = Request.Builder()
                .url("$serverAddress/login")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    // upload ingredients and favorite recipes to server
    fun uploadData(input: User): String {
        val jsonStr: String = Gson().toJson(input)
        val body = RequestBody.create(JSON, jsonStr)
        val request = Request.Builder()
                .url("$serverAddress/upload_data")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    fun downloadData(input: User): String {
        val jsonStr: String = Gson().toJson(input)
        val body = RequestBody.create(JSON, jsonStr)
        val request = Request.Builder()
                .url("$serverAddress/download_data")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }
}