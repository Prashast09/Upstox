package com.trade.upstox.resource

import com.trade.network.Resource
import com.trade.upstox.model.*
import javax.inject.Inject

class HoldingsUseCase
@Inject constructor(private val repo: IHoldingsRepo) : IHoldingsUseCase {
    override suspend fun getHoldings(): Resource<HoldingsResponse> {
        return repo.getHoldings()
    }

    override suspend fun getHoldingsUiState(response: HoldingsResponse?): AppUiState<HoldingsUiState> {
        if (response == null) return AppUiState.Error("No response received")

        val holdings = arrayListOf<HoldingUiState>()
        var currentValue = 0f
        var totalInvestment = 0f
        var todayProfit = 0f
        var totalProfit = 0f

        response.data.map {
            holdings.add(HoldingUiState(it.symbol, it.quantity, it.ltp, it.getTotalProfit()))
            currentValue += it.getCurrentValue()
            totalInvestment += it.getInvestmentValue()
            todayProfit += it.todayProfit()
            totalProfit += it.getTotalProfit()
        }

        val summary = SummaryUiState(currentValue, totalInvestment, todayProfit, totalProfit)

        return AppUiState.Success(HoldingsUiState(holdings, summary))
    }
}