package com.example.shopapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shopapplication.Model.BrandModel
import com.example.shopapplication.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    val brands: LiveData<MutableList<BrandModel>> = repository.brands

    fun loadBrands() = repository.loadBrands()
}