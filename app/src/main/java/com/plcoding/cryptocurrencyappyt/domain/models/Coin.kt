package com.plcoding.cryptocurrencyappyt.domain.models

import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto


data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)

