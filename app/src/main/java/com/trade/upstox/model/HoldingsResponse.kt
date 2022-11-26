package com.trade.upstox.model

import com.google.gson.annotations.SerializedName

data class HoldingsResponse(
    val error: Any?, val data: List<Holding>
)

data class Holding(
    val symbol: String,
    val quantity: Int,
    val ltp: Float,
    val close: Float,
    @SerializedName("avg_price") val buyPrice: Float
) {
    fun getTotalProfit() = quantity * (ltp - buyPrice)
    fun getCurrentValue() = ltp * quantity
    fun getInvestmentValue() = buyPrice * quantity
    fun todayProfit() = quantity * (close - ltp)
}

data class HoldingsUiState(
    val holdings: List<HoldingUiState>, val summary: SummaryUiState
)

data class HoldingUiState(
    val symbol: String, val quantity: Int, val ltp: Float, val profit: Float
)

data class SummaryUiState(
    val currentValue: Float,
    val totalInvestment: Float,
    val todayProfit: Float,
    val totalProfit: Float
)

fun Float.getRoundOff() = "%.2f".format(this)
fun String.getInRupee() = "\u20B9$this"