package com.w36495.everylaundry.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.w36495.everylaundry.RetrofitBuilder
import com.w36495.everylaundry.databinding.FragmentMapBinding
import com.w36495.everylaundry.model.api.LaundryAPI
import com.w36495.everylaundry.model.data.Laundry
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    companion object {
        private var laundryList = listOf<Laundry>()
        private lateinit var mapView: MapView
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        val view = binding.root

        Timber.plant(Timber.DebugTree())

        setInit(view)

        return view
    }

    private fun setInit(view: View) {
        mapView = MapView(view.context)
        binding.mapView.addView(mapView)

        getLaundryList()        // db에서 세탁소 리스트 가져오기
        setLaundryMarker()      // 지도에 마커 표시
    }

    /**
     * database에서 세탁소 정보 불러오기
     */
    private fun getLaundryList() {
        val retrofit = RetrofitBuilder.getClient()
        val laundryAPI = retrofit?.create(LaundryAPI::class.java)

        Thread(Runnable {
            laundryList = laundryAPI?.getLaundryList()?.execute()?.body()!!
        }).start()

        try {
            Thread.sleep(1000)
        } catch (e: Exception) {
            Timber.d("ERROR : getLaundryList() - ${e.message}")
        }
    }

    /**
     * 지도에 마커(세탁소 위치) 표시
     */
    private fun setLaundryMarker() {

        for (laundry in laundryList) {
            val marker = MapPOIItem()
            val point = MapPoint.mapPointWithGeoCoord(laundry.laundryCoordsX, laundry.laundryCoordsY)
            marker.itemName = laundry.laundryName
            marker.tag = laundry.laundryKey.toInt()
            marker.mapPoint = point

            // Type : 0(코인세탁소), 1(일반세탁소)
            if (laundry.laundryType == '0') {
                marker.markerType = MapPOIItem.MarkerType.YellowPin
            } else if (laundry.laundryType == '1') {
                marker.markerType = MapPOIItem.MarkerType.BluePin
            }
            mapView.addPOIItem(marker)
        }
    }
}