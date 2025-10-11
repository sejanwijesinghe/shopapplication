package com.example.shopapplication.Adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.example.shopapplication.R
import com.example.shopapplication.databinding.ViewholderPicsBinding

class PicsAdapter(val items: MutableList<String>, private val onImageSelected: (String) -> Unit):
RecyclerView.Adapter<PicsAdapter.ViewHolder>()
{
    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    inner class ViewHolder(val binding: ViewholderPicsBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicsAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderPicsBinding.inflate(android.view.LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PicsAdapter.ViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            onImageSelected(items[position])
        }

        if(selectedPosition==position){
            holder.binding.root.setBackgroundResource(R.drawable.grey_bg_selected)
        }else{
            holder.binding.colourLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int =items.size


}