package com.sih2023.drdevs.Network

import com.sih2023.drdevs.DataSet.Coordinates
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("your_api_endpoint")
    fun sendCoordinates(@Body coordinates: Coordinates): Call<ResponseBody>
}
