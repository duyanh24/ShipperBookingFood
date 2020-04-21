package com.leduyanh.bookingfoodshipper.view.orderdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.data.dish.Dish
import com.leduyanh.bookingfoodshipper.databinding.DishItemBinding

class DishAdapter(var list: ArrayList<Dish> = ArrayList()):
    RecyclerView.Adapter<DishAdapter.DishViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        return DishViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.dish_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bindViewHolder()
    }

    inner class DishViewHolder(v: DishItemBinding): RecyclerView.ViewHolder(v.root ) {
        private val viewModel = DishViewModel()

        init {
            v.viewmodel = viewModel
        }

        fun bindViewHolder(){
            viewModel.bindData(list[adapterPosition])
        }
    }

    fun updateList(newList: ArrayList<Dish>){
        val diffResult =
            DiffUtil.calculateDiff(DishDiffUtil(list, newList))
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }
}