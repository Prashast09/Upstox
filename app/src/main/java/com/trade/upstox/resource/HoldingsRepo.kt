package com.trade.upstox.resource

import com.trade.network.Resource
import com.trade.network.apiCall
import com.trade.upstox.model.HoldingsResponse
import com.trade.upstox.network.NetworkApiService
import javax.inject.Inject


class HoldingsRepo
@Inject constructor(
    private val api: NetworkApiService
) : IHoldingsRepo {
    override suspend fun getHoldings(): Resource<HoldingsResponse> {
        return apiCall { api.getHoldings() }.toResource { it }
    }
}