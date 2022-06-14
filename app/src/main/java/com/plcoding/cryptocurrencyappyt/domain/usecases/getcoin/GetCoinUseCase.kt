package com.plcoding.cryptocurrencyappyt.domain.usecases.getcoin

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.common.exceptions.NoInternetException
import com.plcoding.cryptocurrencyappyt.common.exceptions.ServerErrorException
import com.plcoding.cryptocurrencyappyt.domain.models.Coin
import com.plcoding.cryptocurrencyappyt.domain.models.CoinDetail
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId)
            emit(Resource.Success(coin))
        } catch (ex: NoInternetException) {
            emit(Resource.Error("Couldn't reach server, check your internet connection"))
        } catch (ex: ServerErrorException) {
            emit(Resource.Error(ex.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}