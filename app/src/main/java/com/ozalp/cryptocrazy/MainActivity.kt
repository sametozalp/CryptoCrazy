package com.ozalp.cryptocrazy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ozalp.cryptocrazy.ui.theme.CryptoCrazyTheme
import com.ozalp.cryptocrazy.view.CryptoDetailScreen
import com.ozalp.cryptocrazy.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            CryptoCrazyTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "crypto_list_screen" // bu ekran ile başla
                ) {

                    composable("crypto_list_screen") {
                        // crypto list screen
                        CryptoListScreen(navController = navController)
                    }

                    composable("crypto_detail_screen/{cryptoID}/{cryptoPrice}", arguments = listOf(
                        navArgument("cryptoID") {
                            type = NavType.StringType
                        },
                        navArgument("cryptoPrice") {
                            type = NavType.StringType
                        }
                    )) {
                        val cryptoID = remember {
                            it.arguments?.getString("cryptoID")
                        }
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoPrice")
                        }
                        // crypto detail screen
                        CryptoDetailScreen(
                            id = cryptoID ?: "",
                            price = cryptoPrice ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}