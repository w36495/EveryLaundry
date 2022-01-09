package com.w36495.everylaundry.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w36495.everylaundry.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        val view = binding.root

        setInit(view)

        return view
    }

    private fun setInit(view: View) {
        val mapView = MapView(view.context)
        binding.mapView.addView(mapView)

    }


}