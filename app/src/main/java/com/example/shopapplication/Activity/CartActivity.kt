package com.example.shopapplication.Activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopapplication.Adapter.CartAdapter
import com.example.shopapplication.ChangeNumberItemsListener
import com.example.shopapplication.ManagmentCart
import com.example.shopapplication.R
import com.example.shopapplication.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        managmentCart = ManagmentCart(this)
        initView()
        initCartList()
        calculateCart()
    }

    private fun initView() {
        binding.backBtn.setOnClickListener {
            finish() }


    }

    private fun initCartList() {
        binding.apply {
            viewCart.layoutManager= LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)

            viewCart.adapter = CartAdapter(managmentCart.getListCart(), this@CartActivity, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })
            emptyTxt.visibility = if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE

            scrollView2.visibility=
                if (managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE

        }
    }
    private fun calculateCart(){
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100.0)/100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100.0)/100.0
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100.0)/100.0

        with(binding){
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            totalTxt.text = "$$total"
            deliveryTxt.text = "$$delivery"
        }
    }
}