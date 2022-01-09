package com.w36495.everylaundry.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w36495.everylaundry.databinding.FragmentBoardBinding
import com.w36495.everylaundry.databinding.FragmentFavoriteBinding
import com.w36495.everylaundry.databinding.FragmentMapBinding

class BoardFragment : Fragment() {

    private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBoardBinding.inflate(layoutInflater)
        val view = binding.root

        return view
    }
}