package com.trade.upstox.ui.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.trade.upstox.model.HoldingUiState
import com.trade.upstox.model.getInRupee
import com.trade.upstox.model.getRoundOff
import com.trade.upstox.ui.style.h16
import com.trade.upstox.ui.style.h16_bold
import com.trade.upstox.ui.style.h16_semi_bold


@Composable
fun HoldingsUi(holdings: List<HoldingUiState>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(holdings) { index, item ->
            AppHoldingCard(item)
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
            )
            if (index != holdings.size - 1) Divider(
                Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .padding(start = 16.dp), color = Color.LightGray
            )
        }
    }
}

@Composable
fun AppHoldingCard(holding: HoldingUiState) {
    Column(Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = holding.symbol, style = h16_bold, color = Color.Black)
            Text(text = buildAnnotatedString {
                append("LTP: ")
                holding.ltp.getRoundOff().getInRupee().let {
                    withStyle(style = h16_semi_bold.toSpanStyle()) {
                        append(it)
                    }
                }
            }, color = Color.Black)
        }

        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = holding.quantity.toString(), style = h16, color = Color.Black)

            Text(text = buildAnnotatedString {
                append("P/L: ")
                holding.profit.getRoundOff().getInRupee().let {
                    withStyle(style = h16_semi_bold.toSpanStyle()) {
                        append(it)
                    }
                }
            }, color = Color.Black)
        }
    }
}