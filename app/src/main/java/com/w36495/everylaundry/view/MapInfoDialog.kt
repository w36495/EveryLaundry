package com.w36495.everylaundry.view

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.w36495.everylaundry.R
import com.w36495.everylaundry.databinding.DialogMapInfoBinding
import com.w36495.everylaundry.model.data.Laundry
import kotlinx.coroutines.InternalCoroutinesApi
import timber.log.Timber


class MapInfoDialog(private val laundry: Laundry) : DialogFragment() {

    private lateinit var binding: DialogMapInfoBinding
    private val size = Point()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogMapInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        Timber.plant(Timber.DebugTree())

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 현재 display의 크기를 구한 후 dialog의 크기 설정
        val windowManager = view.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        display.getSize(size)

        return view
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // laundryType : 0(코인), 1(일반)
        if (laundry.laundryType == '0') {
            binding.mapInfoType.text = getString(R.string.tv_coin)
        } else if (laundry.laundryType == '1') {
            binding.mapInfoType.text = getString(R.string.tv_normal)
        }
        binding.mapInfoName.text = laundry.laundryName
        binding.mapInfoTel.text = laundry.laundryTel
        binding.mapInfoAddress.text = laundry.laundryAddress

        binding.mapInfoClose.setOnClickListener {
            dismiss()
        }

    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.9).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}