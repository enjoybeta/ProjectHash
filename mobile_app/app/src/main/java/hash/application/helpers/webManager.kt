package hash.application.helpers

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

/**
 * Created by gouji on 3/18/2018.
 */
//use Singleton by object
object WebManager {
    private const val serverAddress: String = "https://www.enjoybeta.com"

    private val client: OkHttpClient = OkHttpClient()

    init {
        try {
            val request = Request.Builder()
                    .url("$serverAddress/")
                    .build()
            val response: Response = client.newCall(request).execute()//synchronous
        } catch (e: Exception) {
            Log.e("log_WebManager",e.stackTrace.toString())
        }
    }

    fun getToday1(): String {
        val request = Request.Builder()
                .url("$serverAddress/today1")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string().toString()
    }

    fun getToday2(): String {
        val request = Request.Builder()
                .url("$serverAddress/today2")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string().toString()
    }

    fun getToday3(): String {
        val request = Request.Builder()
                .url("$serverAddress/today3")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string().toString()
    }

    fun getToday4(): String {
        val request = Request.Builder()
                .url("$serverAddress/today4")
                .build()
        val response: Response = client.newCall(request).execute()//synchronous
        assert(response.code() == 200)
        return response.body()!!.string().toString()
    }
}