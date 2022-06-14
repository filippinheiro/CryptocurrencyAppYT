package com.plcoding.cryptocurrencyappyt.presentation.coindetail

import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)