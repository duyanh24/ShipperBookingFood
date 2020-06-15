package com.leduyanh.bookingfoodshipper.view.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.neworder.NewOrderActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var googleMap: GoogleMap? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val homeViewModel:HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),500)
        }
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync {

            it.isMyLocationEnabled = true
            it.uiSettings.isZoomControlsEnabled = true
            googleMap = it
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                if(location != null && googleMap != null){
                    animateCamera(LatLng(location.latitude,location.longitude))
                }
            }

        val sharePreference  = SaveSharedPreference(activity!!)
        var statusShipper = sharePreference.getInt(SaveSharedPreference.STATUS_SHIPPER)
        if(statusShipper == 0){
            btnHomeActiveStatus.setImageResource(R.drawable.icon_power_red)
            txtHomeStatusShipper.text = "Đang bận"
            txtHomeStatusShipper.setTextColor(Color.RED)
        }else{
            btnHomeActiveStatus.setImageResource(R.drawable.icon_power_green)
            txtHomeStatusShipper.text = "Sẵn sàng"
            txtHomeStatusShipper.setTextColor(Color.GREEN)
        }

        btnHomeActiveStatus.setOnClickListener {
            val statusShipper = txtHomeStatusShipper.text.toString()
            if(statusShipper == "Sẵn sàng"){
                btnHomeActiveStatus.setImageResource(R.drawable.icon_power_red)
                txtHomeStatusShipper.text = "Đang bận"
                txtHomeStatusShipper.setTextColor(Color.RED)
                homeViewModel.changeStatusShipper(0)
                sharePreference.putInt(SaveSharedPreference.STATUS_SHIPPER.first,0)
            }else{
                btnHomeActiveStatus.setImageResource(R.drawable.icon_power_green)
                txtHomeStatusShipper.text = "Sẵn sàng"
                txtHomeStatusShipper.setTextColor(Color.GREEN)
                homeViewModel.changeStatusShipper(1)
                sharePreference.putInt(SaveSharedPreference.STATUS_SHIPPER.first,1)
            }
        }
        txtHome.setOnClickListener {
            val intent = Intent(activity!!, NewOrderActivity::class.java)
            startActivity(intent)
        }
    }

    private fun animateCamera(latLng: LatLng) {
        val cameraUpdate = buildCameraUpdate(latLng)
        googleMap?.animateCamera(cameraUpdate, 10, null)
    }

    private fun buildCameraUpdate(latLng: LatLng): CameraUpdate {
        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .tilt(18.toFloat())
            .zoom(15.toFloat())
            .build()
        return CameraUpdateFactory.newCameraPosition(cameraPosition)
    }
}
