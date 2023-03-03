package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aayar94.earthquakes.databinding.FragmentLastEarthquakesBinding
import com.aayar94.earthquakes.model.EarthquakeModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastEarthquakesFragment : Fragment() {
    private var mBinding: FragmentLastEarthquakesBinding? = null
    private val binding get() = mBinding!!


    val mAdapter: AdapterLastEarthquakesRV by lazy { AdapterLastEarthquakesRV() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLastEarthquakesBinding.inflate(layoutInflater)

        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.earthquakesRV.adapter = mAdapter.also {
            it.setItems(
                listOf(
                    EarthquakeModel(1.0,"polatlı","03/03/2013 22:23",null,null),
                    EarthquakeModel(1.0,"polatlı","03/03/2013 22:23",null,null),
                    EarthquakeModel(1.0,"polatlı","03/03/2013 22:23",null,null),
                )

            )
        }
    }


}