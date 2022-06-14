package com.plcoding.cryptocurrencyappyt.data.repositories

import com.plcoding.cryptocurrencyappyt.common.exceptions.NoInternetException
import com.plcoding.cryptocurrencyappyt.common.exceptions.ServerErrorException
import com.plcoding.cryptocurrencyappyt.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.domain.models.Coin
import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        try {
            val coinDtoList = api.getCoins()
            return coinDtoList.toCoinList()
        } catch (exception: HttpException) {
            throw ServerErrorException(exception.localizedMessage)
        } catch (exception: IOException) {
            throw NoInternetException()
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetail {
        try {
            val coinDetailDto = api.getCoinById(coinId)
            return coinDetailDto.toCoinDetail()
        } catch (exception: HttpException) {
            throw ServerErrorException(exception.localizedMessage)
        } catch (exception: IOException) {
            throw NoInternetException()
        }
    }
}

private fun List<CoinDto>.toCoinList(): List<Coin> {
    return this.map {
        it.toCoin()
    }
}

private fun CoinDto.toCoin(): Coin {
    return Coin(
        id = this.id,
        isActive = this.isActive,
        name = this.name,
        rank = this.rank,
        symbol = this.symbol,
    )
}

private fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        coinId = id,
        name = name,
        description = description,
        symbol = symbol,
        rank = rank,
        isActive = isActive,
        tags = tags.map { it.name },
        team = team
    )
}