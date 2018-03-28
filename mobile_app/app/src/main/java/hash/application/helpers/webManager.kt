package hash.application.helpers

import android.util.Log
import okhttp3.*


/**
 * Created by gouji on 3/18/2018.
 */
//use Singleton by object
object WebManager {
    private const val serverAddress: String = "https://www.enjoybeta.com"
    private val JSON = MediaType.parse("application/json; charset=utf-8")

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

    fun searchByName(input: String): String {
        val tmp = "{\"keyword\":\"$input\"}"
        val body = RequestBody.create(JSON, tmp)
        val request = Request.Builder()
                .url("$serverAddress/searchPrecise")
                .post(body)
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string()
    }
}