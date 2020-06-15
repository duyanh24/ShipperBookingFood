package com.leduyanh.bookingfoodshipper.view.currentorder

import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.data.models.dish.Dish
import com.leduyanh.bookingfoodshipper.data.models.order.Order
import com.leduyanh.bookingfoodshipper.data.repository.ICallBack
import com.leduyanh.bookingfoodshipper.data.repository.OrderRepository
import com.leduyanh.bookingfoodshipper.data.repository.ShipperRepository
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.orderdetail.DishAdapter

class CurrentOrderViewModel(
    private val orderRepository: OrderRepository,
    private val shipperRepository: ShipperRepository
): ViewModel(){

    val currentOrder: MutableLiveData<Order> = MutableLiveData()
    val addressDirection: MutableLiveData<String> = MutableLiveData()
    val totalPrice: MutableLiveData<String> = MutableLiveData()

    var directionRestaurant = ""
    var directionCustomer = ""

    val customerName: MutableLiveData<String> = MutableLiveData()
    val storeName: MutableLiveData<String> = MutableLiveData()
    val customerAddress: MutableLiveData<String> = MutableLiveData()
    val storeAddress: MutableLiveData<String> = MutableLiveData()

    val customerPhone: MutableLiveData<String> = MutableLiveData()

    var adapter = DishAdapter()

    fun getDataOrder(){
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)
        orderRepository.getCurrentOrder(authorization, object : ICallBack<Order>{
            override fun getData(data: Order) {
                currentOrder.value = data
                totalPrice.value = data.totalPrice.toString() + " VNĐ"

                directionRestaurant = data.store.name + ", " + data.store.address
                directionCustomer = data.user.name + ", " + data.addressCus
                addressDirection.value = directionRestaurant

                customerName.value = data.user.name
                storeName.value = data.store.name
                customerAddress.value = data.addressCus
                storeAddress.value = data.store.address

                customerPhone.value = data.user.phone
            }
            override fun getError(mess: String) {
                Toast.makeText(MyApplication.instance,mess+"",Toast.LENGTH_LONG).show()
            }
        })
    }

    fun onclickBtnDirection(view : View){
        addressDirection.value = directionCustomer
    }

    fun getDataOrderDetail(){
        getDataOrder()
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)

        orderRepository.getDataOrderDetail(authorization,5,object : ICallBack<List<Dish>>{
            override fun getData(data: List<Dish>) {
                adapter.updateList(data as ArrayList<Dish>)
            }
            override fun getError(mess: String) {
            }
        })
    }

    fun changeStatusShipper(isOnline: Int){
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)
        val idShiper = sharedPreference?.getInt(SaveSharedPreference.ID)
        shipperRepository.updateStatusShipper(authorization,idShiper!!,isOnline,object :ICallBack<String>{
            override fun getError(mess: String) {
            }

            override fun getData(data: String) {
            }
        })
    }

    fun changeStatusOrder(statusOrder: Int){
        val sharedPreference = MyApplication.applicationContext()?.let { SaveSharedPreference(it) }
        val authorization = sharedPreference?.getString(SaveSharedPreference.TOKEN)
        val idShiper = sharedPreference?.getInt(SaveSharedPreference.ID)
        orderRepository.updateStatusOrder(authorization,idShiper!!,statusOrder,object :ICallBack<String>{
            override fun getError(mess: String) {
            }

            override fun getData(data: String) {
            }
        })
    }
}