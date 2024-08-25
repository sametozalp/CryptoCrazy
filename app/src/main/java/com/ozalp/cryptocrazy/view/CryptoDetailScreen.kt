package com.ozalp.cryptocrazy.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.ozalp.cryptocrazy.model.CryptoItem
import com.ozalp.cryptocrazy.util.Resource
import com.ozalp.cryptocrazy.viewmodel.CryptoDetailViewModel
import kotlinx.coroutines.launch

@Composable
fun CryptoDetailScreen(
    id: String,
    price: String,
    navController: NavController,
    viewModel: CryptoDetailViewModel = hiltViewModel()
) {

    /*
    // step 1 -> Wrong

    val scope = rememberCoroutineScope()

    var cryptoItem by remember {
        mutableStateOf<Resource<List<CryptoItem>>>(Resource.Loading())
    }

    scope.launch {
        cryptoItem = viewModel.getCrypto(id)
        println(cryptoItem.data)
    }
     */
    /*
         //Better
        var cryptoItem by remember {
            mutableStateOf<Resource<CryptoItem>>(Resource.Loading())
        }

        LaunchedEffect(key1 = Unit) {
            cryptoItem = viewModel.getCrypto(id)
            println(cryptoItem)
        }
     */

    val cryptoItem = produceState<Resource<CryptoItem>>(initialValue = Resource.Loading()) {
        value = viewModel.getCrypto(id)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when (cryptoItem) {

                is Resource.Success -> {

                    val selectedCrypto = cryptoItem.data!!
                    Text(
                        text = selectedCrypto.name,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )

                    Image(
                        painter = rememberImagePainter(data = selectedCrypto.logo_url),
                        contentDescription = selectedCrypto.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    Text(
                        text = price,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary,
                        textAlign = TextAlign.Center
                    )
                }

                is Resource.Error -> {
                    Text(text = cryptoItem.message!!)
                }

                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }

    }
}