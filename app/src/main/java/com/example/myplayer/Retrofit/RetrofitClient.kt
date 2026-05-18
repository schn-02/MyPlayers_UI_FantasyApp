package com.example.myplayer.Retrofit

import android.os.Build
import com.example.myplayer.Interceptor.FirebaseInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val EMULATOR_BASE_URL = "http://10.0.2.2:9090/"


    private const val PHONE_BASE_URL = "http://192.168.29.111:9090/"

    private fun isRunningOnEmulator(): Boolean {
        return (
                Build.FINGERPRINT.startsWith("generic") ||
                        Build.FINGERPRINT.startsWith("unknown") ||
                        Build.MODEL.contains("google_sdk", ignoreCase = true) ||
                        Build.MODEL.contains("Emulator", ignoreCase = true) ||
                        Build.MODEL.contains("Android SDK built for x86", ignoreCase = true) ||
                        Build.MANUFACTURER.contains("Genymotion", ignoreCase = true) ||
                        Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic") ||
                        Build.PRODUCT == "google_sdk" ||
                        Build.HARDWARE.contains("goldfish", ignoreCase = true) ||
                        Build.HARDWARE.contains("ranchu", ignoreCase = true)
                )
    }

    private fun getBaseUrl(): String {
        return if (isRunningOnEmulator()) {
            EMULATOR_BASE_URL
        } else {
            PHONE_BASE_URL
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(FirebaseInterceptor())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val AuthData: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)
}