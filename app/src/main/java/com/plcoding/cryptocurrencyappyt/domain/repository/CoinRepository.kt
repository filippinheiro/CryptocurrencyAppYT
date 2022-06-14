package com.plcoding.cryptocurrencyappyt.domain.repository

import com.plcoding.cryptocurrencyappyt.domain.models.Coin
import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail

interface CoinRepository {
    suspend fun getCoins(): List<Coin>
    suspend fun getCoinById(coinId: String): CoinDetail
}