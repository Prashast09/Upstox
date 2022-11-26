package com.trade.upstox.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trade.network.Resource
import com.trade.upstox.model.AppUiState
import com.trade.upstox.model.HoldingsUiState
import com.trade.upstox.resource.IHoldingsUseCase
import com.trade.upstox.scheduler.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingsViewModel
@Inject constructor(
    private val useCase: IHoldingsUseCase, private val dispatcher: SchedulerProvider
) : ViewModel() {

    private val _holdingsUiState = MutableStateFlow<AppUiState<HoldingsUiState>>(AppUiState.Start())
    val holdingsUiState: StateFlow<AppUiState<HoldingsUiState>> = _holdingsUiState

    fun getHoldings() {
        viewModelScope.launch(dispatcher.IO) {
            _holdingsUiState.emit(AppUiState.Loading())
            when (val response = useCase.getHoldings()) {
                is Resource.Success -> _holdingsUiState.emit(useCase.getHoldingsUiState(response.data))
                is Resource.Error -> _holdingsUiState.emit(
                    AppUiState.Error(response.message ?: "Some error occurred")
                )
                is Resource.ApiError -> _holdingsUiState.emit(
                    AppUiState.Error(response.message ?: "Server Error")
                )
            }
        }
    }
}