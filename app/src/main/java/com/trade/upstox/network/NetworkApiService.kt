package com.trade.upstox.network

import com.trade.upstox.model.HoldingsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApiService {
    @GET("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    fun getHoldings(): Call<HoldingsResponse>
}