package com.example.shopapplication.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapplication.Adapter.BrandsAdapter
import com.example.shopapplication.ViewModel.MainViewModel
import com.example.shopapplication.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    private val brandsAdapter = BrandsAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI(){
        initBrands()
    }

    private fun initBrands() {
        binding.recyclerViewBrands.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewBrands.adapter=brandsAdapter
        binding.progressBarBrands.visibility= View.VISIBLE

        viewModel.brands.observe(this){
            data->
            brandsAdapter.updateData(data)
            binding.progressBarBrands.visibility= View.GONE
        }
        viewModel.loadBrands()
    }
}