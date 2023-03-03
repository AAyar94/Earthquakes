package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aayar94.earthquakes.databinding.FragmentLastEarthquakesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastEarthquakesFragment : Fragment() {
    private var mBinding: FragmentLastEarthquakesBinding? = null
    private val binding get() = mBinding!!


    val mAdapter: AdapterLastEarthquakesRV by lazy { AdapterLastEarthquakesRV() }

    val viewModel: LastEarthquakesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLastEarthquakesBinding.inflate(layoutInflater)

        setupRecyclerView()
        initObserver()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEarthquakes()
    }
    private fun setupRecyclerView() {
        binding.earthquakesRV.adapter = mAdapter
    }

    fun initObserver() {
        viewModel.earthquakes.observe(viewLifecycleOwner) {
            mAdapter.setItems(it)
        }
    }


}