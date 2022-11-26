package com.trade.upstox.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.trade.upstox.model.AppUiState
import com.trade.upstox.model.HoldingsUiState
import com.trade.upstox.ui.app.AppBar
import com.trade.upstox.ui.app.HoldingsUi
import com.trade.upstox.ui.app.SummaryUi
import com.trade.upstox.viewmodel.HoldingsViewModel

@Composable
fun UpstoxApp() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        AppBar()
        AppContent()
    }
}

@Composable
fun AppContent() {
    Column {
        val viewModel: HoldingsViewModel = hiltViewModel()
        val response by viewModel.holdingsUiState.collectAsState()
        when (response) {
            is AppUiState.Loading -> {
                AppLoading()
            }
            is AppUiState.Success -> {
                AppSuccess((response as AppUiState.Success).response)
            }

            is AppUiState.Error -> {
                AppError((response as AppUiState.Error).msg)
            }

            is AppUiState.Start -> doNothing()
        }
    }
}


@Composable
private fun AppSuccess(response: HoldingsUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HoldingsUi(response.holdings)
        SummaryUi(response.summary)
    }
}


@Composable
private fun AppError(error: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = error)
    }
}

@Composable
private fun AppLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

private fun doNothing() {

}