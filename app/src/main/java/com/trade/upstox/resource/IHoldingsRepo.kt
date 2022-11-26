package com.trade.upstox.resource

import com.trade.network.Resource
import com.trade.upstox.model.HoldingsResponse

interface IHoldingsRepo {
    suspend fun getHoldings(): Resource<HoldingsResponse>
}