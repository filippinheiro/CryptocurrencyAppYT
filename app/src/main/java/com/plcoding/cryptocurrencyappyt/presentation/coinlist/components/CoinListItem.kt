package com.plcoding.cryptocurrencyappyt.presentation.coinlist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plcoding.cryptocurrencyappyt.R
import com.plcoding.cryptocurrencyappyt.domain.models.Coin

@Preview
@Composable
fun CoinListItemPreview() {
    CoinListItem(
        coin = Coin(
            name = "Bitcoin",
            id = "btc-bitcoin",
            symbol = "BTC",
            isActive = true,
            rank = 1
        ),
        onItemClicked = {
            //
        }
    )
}

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClicked: (Coin) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClicked(coin) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(
                id = R.string.coin_detail,
                coin.rank, coin.name, coin.symbol,
            ),
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = if (coin.isActive) stringResource(R.string.active_coin_string) else stringResource(
                R.string.inactive_coin_string
            ),
            color = if (coin.isActive) Color.Green else Color.Red,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(CenterVertically)
        )
    }
}