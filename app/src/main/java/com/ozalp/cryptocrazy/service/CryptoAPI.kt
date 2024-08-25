package com.ozalp.cryptocrazy.service

import com.ozalp.cryptocrazy.model.CryptoItem
import com.ozalp.cryptocrazy.model.CryptoListItem
import retrofit2.http.GET

interface CryptoAPI {

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/cryptolist.json")
    suspend fun getCryptoList(): List<CryptoListItem>

    @GET("atilsamancioglu/IA32-CryptoComposeData/main/crypto.json")
    suspend fun getCrypto(): CryptoItem
}