package com.ozalp.cryptocrazy.repository

import com.ozalp.cryptocrazy.model.CryptoItem
import com.ozalp.cryptocrazy.model.CryptoListItem
import com.ozalp.cryptocrazy.service.CryptoAPI
import com.ozalp.cryptocrazy.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScoped
class CryptoRepository @Inject constructor(
    private val api: CryptoAPI
){

    suspend fun getCryptoList(): Resource<List<CryptoListItem>> {
        val response = try {
            api.getCryptoList()
        } catch (e: Exception) {
            return Resource.Error("error")
        }

        return Resource.Success(response)
    }

    suspend fun getCrypto(id: String): Resource<CryptoItem> {
        val response = try {
            api.getCrypto()
        } catch (e: Exception) {
            return Resource.Error("error")
        }

        return Resource.Success(response)
    }

}