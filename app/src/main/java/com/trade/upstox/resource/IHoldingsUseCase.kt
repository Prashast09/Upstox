package com.trade.upstox.resource

import com.trade.network.Resource
import com.trade.upstox.model.AppUiState
import com.trade.upstox.model.HoldingsResponse
import com.trade.upstox.model.HoldingsUiState

interface IHoldingsUseCase {
    suspend fun getHoldings(): Resource<HoldingsResponse>
    suspend fun getHoldingsUiState(response: HoldingsResponse?): AppUiState<HoldingsUiState>
}