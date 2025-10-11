package com.example.shopapplication.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.shopapplication.Adapter.BrandsAdapter
import com.example.shopapplication.Adapter.PopularAdapter
import com.example.shopapplication.Adapter.SliderAdapter
import com.example.shopapplication.Model.SliderModel
import com.example.shopapplication.ViewModel.MainViewModel
import com.example.shopapplication.databinding.ActivityMainBinding

class DashboardActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    private lateinit var binding: ActivityMainBinding

    private val brandsAdapter = BrandsAdapter(mutableListOf())

    private val popularAdapter= PopularAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI(){
        initBrands()
        initBanner()
        initRecommended()
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        binding.cartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }

    private fun initRecommended() {
        binding.recyclerViewRecommended.layoutManager= GridLayoutManager(this, 2)
        binding.recyclerViewRecommended.adapter=popularAdapter
        binding.progressBarRecommendation.visibility= View.VISIBLE
        viewModel.popular.observe(this){
            data->
            popularAdapter.updateData(data)
            binding.progressBarRecommendation.visibility= View.GONE
        }
        viewModel.loadPopular()

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

    private fun setupBanners(image: List<SliderModel>){
        binding.viewpagerSlider.apply {
            adapter= SliderAdapter(image, this)
            clipToPadding=false
            clipChildren=false
            offscreenPageLimit=3
            (getChildAt(0) as? RecyclerView)?.overScrollMode=
                RecyclerView.OVER_SCROLL_NEVER
            setPageTransformer  (CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
            })
        }
        binding.dotIndicator.apply {
            visibility = if (image.size > 1) View.VISIBLE else View.GONE
            if (image.size > 1) attachTo(binding.viewpagerSlider)
        }
    }

    private fun initBanner(){
        binding.progressBarBanner.visibility= View.VISIBLE
        viewModel.banners.observe(this){
            items->
            setupBanners(items)
            binding.progressBarBanner.visibility= View.GONE
        }
        viewModel.loadBanners()
    }
}