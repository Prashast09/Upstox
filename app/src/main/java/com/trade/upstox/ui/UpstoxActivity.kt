package com.trade.upstox.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.trade.upstox.viewmodel.HoldingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpstoxActivity : AppCompatActivity() {
    private val viewModel by viewModels<HoldingsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { UpstoxApp() }
        viewModel.getHoldings()
    }

}