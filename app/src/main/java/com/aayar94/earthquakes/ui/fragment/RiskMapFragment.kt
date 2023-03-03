package com.aayar94.earthquakes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.earthquakes.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RiskMapFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_risk_map, container, false)
    }


}