package com.leduyanh.bookingfoodshipper.view.currentorder

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.leduyanh.bookingfoodshipper.MyApplication
import com.leduyanh.bookingfoodshipper.R
import com.leduyanh.bookingfoodshipper.databinding.FragmentDirectionBinding
import com.leduyanh.bookingfoodshipper.utils.SaveSharedPreference
import com.leduyanh.bookingfoodshipper.view.main.HomeActivity
import kotlinx.android.synthetic.main.fragment_direction.*
import org.json.JSONException
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlin.collections.ArrayList


class DirectionFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var binding: FragmentDirectionBinding
    private val currentOrderViewModel:CurrentOrderViewModel by viewModel()
    lateinit var currenPoint:LatLng

    var STATUS_DIRECTION = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_direction,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = currentOrderViewModel
        currentOrderViewModel.getDataOrder(1)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapDirection) as SupportMapFragment?
        mapFragment?.getMapAsync {

            it.isMyLocationEnabled = true
            it.uiSettings.isZoomControlsEnabled = true
            googleMap = it
        }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                if(location != null){
                    currenPoint = LatLng(location.latitude,location.longitude)
                    animateCamera(LatLng(location.latitude,location.longitude))
                }
            }

        btnOrderDetail.setOnClickListener {
            (context as CurrentOrderActivity).moveScreen(CurrentOrderDetailFragment(),true)
        }

        btnCall.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    activity!!,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                val uri = Uri.parse("tel:0" + currentOrderViewModel.customerPhone.value)
                val i = Intent(Intent.ACTION_CALL, uri)
                startActivity(i)
            }
        }

        btnSendMessage.setOnClickListener {
            val number = "0"+ currentOrderViewModel.customerPhone.value
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.fromParts("sms", number, null)
                )
            )
        }

        btnDirection.setOnClickListener {
            when(STATUS_DIRECTION){
                1->{
                    direction(currentOrderViewModel.storeAddress.value)
                    btnDirection.text = "Tìm đường giao hàng"
                    STATUS_DIRECTION = 2
                }
                2->{
                    direction(currentOrderViewModel.customerAddress.value)
                    btnDirection.text = "Hoàn Thành"
                    STATUS_DIRECTION = 3
                }
                3->{
                    // goi api change status order chưa đc
                    currentOrderViewModel.changeStatusOrder(2)
                    val sharePreference  = SaveSharedPreference(MyApplication.instance)
                    sharePreference.putBoolean(SaveSharedPreference.DELIVERY.first,false)
                    val intent = Intent(activity,HomeActivity::class.java)
                    startActivity(intent)
                    activity!!.finish()
                }
            }
        }
    }

    private fun direction(address: String?){
        googleMap.clear()

        var enpointFix:LatLng?
        enpointFix = if(STATUS_DIRECTION == 1){
            LatLng(20.9712482,105.825606)
        }else{
            LatLng(21.0088408,105.8601192)
        }

        var endPoint:LatLng? = null
        try{
            endPoint = getLocationFromAddress(activity!!,address!!)
        }catch (e: Exception){

        }

        if(endPoint == null){
            endPoint = enpointFix
        }
        val url = getRequestUrl(currenPoint, endPoint)
        TaskRequestDirections().execute(url)

        googleMap.addMarker(
            MarkerOptions()
                .position(endPoint)
        )

        val bounds = LatLngBounds.Builder()
            .include(currenPoint)
            .include(endPoint)
            .build()
        val padding = 200
        val cu = CameraUpdateFactory.newLatLngBounds(bounds, padding)
        googleMap.animateCamera(cu)
    }

    private fun animateCamera(latLng: LatLng) {
        val cameraUpdate = buildCameraUpdate(latLng)
        googleMap.animateCamera(cameraUpdate, 10, null)
    }

    private fun buildCameraUpdate(latLng: LatLng): CameraUpdate {
        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .tilt(18.toFloat())
            .zoom(15.toFloat())
            .build()
        return CameraUpdateFactory.newCameraPosition(cameraPosition)
    }

    private fun getRequestUrl(origin: LatLng, dest: LatLng): String? {
        val str_org = "waypoint0=" + origin.latitude + "%2C" + origin.longitude
        val str_dest = "waypoint1=" + dest.latitude + "%2C" + dest.longitude
        val app_id = "app_id=" + resources.getString(R.string.app_id)
        val app_code = "app_code=" + resources.getString(R.string.app_code)
        val mode = "mode=fastest%3Bcar%3Btraffic%3Aenabled"
        val departure = "departure=now"
        val output = "json"
        val param =
            "$app_id&$app_code&$str_org&$str_dest&$mode&$departure"
        return "https://route.api.here.com/routing/7.2/calculateroute.$output?$param"
    }

    inner class TaskRequestDirections : AsyncTask<String?, Void?, String>() {

        override fun onPostExecute(s: String) {
            super.onPostExecute(s)
            TaskParser().execute(s)
        }

        override fun doInBackground(vararg p0: String?): String? {
            var responseString:String? = ""
            try {
                responseString = requestDirection(p0[0]!!)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return responseString
        }
    }

    inner class TaskParser : AsyncTask<String?, Void?, List<List<HashMap<String, String>>>?>() {
        override fun doInBackground(vararg p0: String?): List<List<HashMap<String, String>>>? {
            var jsonObject: JSONObject? = null
            var routes: List<List<HashMap<String, String>>>? =
                null
            try {
                jsonObject = JSONObject(p0[0])
                val directionsParser = DirectionsParser()
                routes = directionsParser.parse(jsonObject)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            return routes
        }

        override fun onPostExecute(lists: List<List<HashMap<String, String>>>?) {
            var points = ArrayList<LatLng>()
            var polylineOptions: PolylineOptions? = null
            for (path in lists!!) {
                polylineOptions = PolylineOptions()
                for (point in path) {
                    val lat = point["lat"]!!.toDouble()
                    val lon = point["long"]!!.toDouble()
                    points.add(LatLng(lat, lon))
                }
                polylineOptions.addAll(points)
                polylineOptions.width(15f)
                polylineOptions.color(Color.BLUE)
                polylineOptions.geodesic(true)
            }
            if (polylineOptions != null) {
                googleMap.addPolyline(polylineOptions)
            } else {
                Toast.makeText(activity, "Direction not found", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    @Throws(IOException::class)
    private fun requestDirection(reqUrl: String): String? {
        var responseString = ""
        var inputStream: InputStream? = null
        var httpURLConnection: HttpURLConnection? = null
        try {
            val url = URL(reqUrl)
            httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.connect()
            //Get the response result
            inputStream = httpURLConnection!!.inputStream
            val inputStreamReader =
                InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            val stringBuffer = StringBuffer()
            var line: String? = ""
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuffer.append(line)
            }
            responseString = stringBuffer.toString()
            bufferedReader.close()
            inputStreamReader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            inputStream?.close()
            httpURLConnection!!.disconnect()
        }
        return responseString
    }

    private fun  getLocationFromAddress(context: Context, strAddress: String):LatLng? {
        val coder = Geocoder(context)
        val address:List<Address>
        var p1: LatLng?  = null

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5)
            if (address == null) {
                return null
            }

            var location = address.get(0)
            p1 =  LatLng(location.getLatitude(), location.getLongitude())

        } catch (ex: IOException ) {
            ex.printStackTrace()
        }
        return p1
    }
}
