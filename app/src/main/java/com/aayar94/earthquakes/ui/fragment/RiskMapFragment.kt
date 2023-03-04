package com.aayar94.earthquakes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.aayar94.earthquakes.R
import com.aayar94.earthquakes.databinding.FragmentRiskMapBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RiskMapFragment : Fragment() {
    private var mBinding : FragmentRiskMapBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding= FragmentRiskMapBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        binding.imageView.load(R.raw.risk_map)
        return binding.root
    }


}