package com.w36495.everylaundry.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.w36495.everylaundry.databinding.FragmentMapBinding
import com.w36495.everylaundry.model.data.Laundry
import com.w36495.everylaundry.viewModel.LaundryViewModel
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import net.daum.mf.map.api.MapView.POIItemEventListener
import timber.log.Timber

class MapFragment : Fragment(), POIItemEventListener {

    private lateinit var binding: FragmentMapBinding
    private lateinit var viewModel: LaundryViewModel

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

        viewModel = ViewModelProvider(requireActivity())[LaundryViewModel::class.java]

        Timber.plant(Timber.DebugTree())

        setInit(view)

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.laundryList.observe(requireActivity(), Observer { _laundryList ->
            laundryList = _laundryList
        })
    }

    private fun setInit(view: View) {
        mapView = MapView(view.context)
        binding.mapView.addView(mapView)

        getLaundryList()        // db에서 세탁소 리스트 가져오기
        setLaundryMarker()      // 마커 표시
    }

    /**
     * database에서 세탁소 정보 불러오기
     */
    private fun getLaundryList() {
        viewModel.getLaundryList()
    }

    /**
     * 지도에 마커(세탁소 위치) 표시
     */
    private fun setLaundryMarker() {
        for (laundry in laundryList) {
            val marker = MapPOIItem()
            val point =
                MapPoint.mapPointWithGeoCoord(laundry.laundryCoordsX, laundry.laundryCoordsY)
            marker.itemName = laundry.laundryName
            marker.tag = laundryList.indexOf(laundry)
            marker.mapPoint = point

            // Type : 0(코인세탁소), 1(일반세탁소)
            if (laundry.laundryType == '0') {
                marker.markerType = MapPOIItem.MarkerType.YellowPin
            } else if (laundry.laundryType == '1') {
                marker.markerType = MapPOIItem.MarkerType.BluePin
            }
            mapView.addPOIItem(marker)

            mapView.setPOIItemEventListener(this)
        }
    }

    /**
     * 세탁소의 정보를 볼 수 있는 다이얼로그 띄우기
     */
    private fun showLaundryInfo(laundry: Laundry) {
        MapInfoDialog(laundry).show(requireFragmentManager(), "MapInfoDialog")
    }

    override fun onPOIItemSelected(mapView: MapView?, item: MapPOIItem?) {
        showLaundryInfo(laundryList[item!!.tag])
    }

    override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
        // Deprecated
    }

    override fun onCalloutBalloonOfPOIItemTouched(
        mapView: MapView?,
        item: MapPOIItem?,
        type: MapPOIItem.CalloutBalloonButtonType?
    ) {
        showLaundryInfo(laundryList[item!!.tag])
    }

    override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {

    }

}

