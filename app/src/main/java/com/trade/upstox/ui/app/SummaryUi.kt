package com.trade.upstox.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.trade.upstox.model.SummaryUiState
import com.trade.upstox.model.getInRupee
import com.trade.upstox.model.getRoundOff
import com.trade.upstox.ui.style.h16
import com.trade.upstox.ui.style.h18_bold

@Composable
fun SummaryUi(summaryUiState: SummaryUiState) {
    Column(
        Modifier
            .background(color = Color.White)
            .padding(start = 16.dp, end = 16.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SummaryRow(
            title = "Current Value",
            value = summaryUiState.currentValue
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )

        SummaryRow(
            title = "Total Investment",
            value = summaryUiState.totalInvestment
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        SummaryRow(
            title = "Today's Profit & Loss",
            value = summaryUiState.todayProfit
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        SummaryRow(
            title = "Profit & Loss",
            value = summaryUiState.totalProfit
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
        )

        Divider(
            modifier = Modifier
                .width(90.dp)
                .height(2.dp), color = Color.Black
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
        )
    }
}

@Composable
private fun SummaryRow(title: String, value: Float) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$title:", style = h18_bold)
        Text(text = value.getRoundOff().getInRupee(), style = h16)
    }
}