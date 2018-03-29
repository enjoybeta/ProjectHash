package hash.application.helpers

import android.util.Log
import com.google.gson.Gson
import hash.application.dataType.SearchCoarse
import hash.application.dataType.SearchPrecise
import okhttp3.*

/**
 * Created by gouji on 3/18/2018.
 */
//use Singleton by object
object WebManager {
    private const val serverAddress: String = "https://www.enjoybeta.com"
    private val JSON: MediaType? = MediaType.parse("application/json; charset=utf-8")

    private val client: OkHttpClient = OkHttpClient()

    init {
        try {
            val request = Request.Builder()
                    .url("$serverAddress/")
                    .build()
            val response: Response = client.newCall(request).execute()//synchronous
        } catch (e: Exception) {
            Log.e("log_WebManager", e.stackTrace.toString())
        }
    }

    fun getToday1(): String {
        val request = Request.Builder()
                .url("$serverAddress/today1")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    fun getToday2(): String {
        val request = Request.Builder()
                .url("$serverAddress/today2")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    fun getToday3(): String {
        val request = Request.Builder()
                .url("$serverAddress/today3")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

    fun getToday4(): String {
        val request = Request.Builder()
                .url("$serverAddress/today4")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }

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

}