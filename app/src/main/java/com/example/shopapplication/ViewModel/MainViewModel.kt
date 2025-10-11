package com.example.shopapplication.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.shopapplication.Model.BrandModel
import com.example.shopapplication.Model.ItemModel
import com.example.shopapplication.Model.SliderModel
import com.example.shopapplication.Repository.MainRepository

class MainViewModel: ViewModel() {
    private val repository= MainRepository()

    val popular: LiveData<MutableList<ItemModel>> = repository.popular
    val banners: LiveData<List<SliderModel>> = repository.banners
    val brands: LiveData<MutableList<BrandModel>> = repository.brands


    fun loadBrands() = repository.loadBrands()
    fun loadBanners() = repository.loadBanners()
    fun loadPopular() = repository.loadPopular()

}