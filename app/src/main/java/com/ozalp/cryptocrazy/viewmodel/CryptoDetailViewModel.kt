package com.ozalp.cryptocrazy.viewmodel

import androidx.lifecycle.ViewModel
import com.ozalp.cryptocrazy.model.CryptoItem
import com.ozalp.cryptocrazy.repository.CryptoRepository
import com.ozalp.cryptocrazy.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val repository: CryptoRepository
) : ViewModel() {


    suspend fun getCrypto(id: String): Resource<CryptoItem> {
        return repository.getCrypto(id)
    }
}