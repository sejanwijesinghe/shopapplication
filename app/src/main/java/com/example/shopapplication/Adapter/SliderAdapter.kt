package com.example.shopapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.UiContext
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.shopapplication.Model.SliderModel
import com.example.shopapplication.R
import kotlinx.coroutines.Runnable

class SliderAdapter (
    private var sliderItems:List<SliderModel>,
    private val viewPage2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewholder>(){
    class SliderViewholder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlider)

            fun setImage(sliderItems: SliderModel,context: Context) {
                val requestOption= RequestOptions().transform(CenterInside())

                Glide.with(context)
                    .load(sliderItems.url)
                    .apply ( requestOption )
                    .into(imageView)
            }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewholder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.slider_item_container, parent,false)

        return SliderViewholder(view)
    }

    override fun onBindViewHolder(holder: SliderViewholder, position: Int) {
        holder.setImage(sliderItems[position], holder.itemView.context)
        if(position==sliderItems.lastIndex-1){
            viewPage2.post(runnable)
        }
    }

    override fun getItemCount() = sliderItems.size

    private val runnable= Runnable {
        sliderItems=sliderItems
        notifyDataSetChanged()
    }
}