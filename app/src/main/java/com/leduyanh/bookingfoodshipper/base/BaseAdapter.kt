package com.leduyanh.bookingfoodshipper.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.databinding.OrderItemBinding
import com.leduyanh.bookingfoodshipper.view.history.OrderViewModel

abstract class BaseAdapter<T> (private val list: ArrayList<T>):
    RecyclerView.Adapter<BaseAdapter<T>.BaseViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                getItemLayout(),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindViewHolder()
    }

    inner class BaseViewHolder(v: OrderItemBinding): RecyclerView.ViewHolder(v.root ),View.OnClickListener {
        private val viewModel = OrderViewModel()

        init {
            v.viewmodel = viewModel
        }

        fun bindViewHolder(){
            viewModel.bindData(getValueItem(adapterPosition))
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View) {
            onClickItem(p0,adapterPosition)
        }
    }

    abstract fun updateList(newList: ArrayList<T>)

    abstract fun getValueItem(adapterPosition: Int): Order

    abstract fun onClickItem(v: View,adapterPosition :Int)

    abstract fun getItemLayout():Int

}
interface OnClickItemListener{
    fun onClickItem(view: View, orderId: Int)
}