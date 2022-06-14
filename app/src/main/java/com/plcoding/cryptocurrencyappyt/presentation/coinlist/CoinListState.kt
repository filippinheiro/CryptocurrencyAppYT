package com.plcoding.cryptocurrencyappyt.presentation.coinlist

import com.plcoding.cryptocurrencyappyt.domain.models.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coinList: List<Coin> = emptyList(),
    val error: String = ""
)