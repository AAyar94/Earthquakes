package com.aayar94.earthquakes.ui.fragment.LastEarthquakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aayar94.earthquakes.databinding.FragmentLastEarthquakesBinding
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastEarthquakesFragment : Fragment() {
    private var mBinding: FragmentLastEarthquakesBinding? = null
    private val binding get() = mBinding!!


    val mAdapter: AdapterLastEarthquakesRV by lazy {
        AdapterLastEarthquakesRV {
            val action =
                LastEarthquakesFragmentDirections.actionLastEarthquakesFragmentToMapsFragment(it)
            findNavController().navigate(action)
        }
    }

    val viewModel: LastEarthquakesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLastEarthquakesBinding.inflate(layoutInflater)

        setupRecyclerView()
        initObserver()
        setStatusAndNavBarColor()

        binding.swipeToRefreshLayout.setOnRefreshListener {
            binding.swipeToRefreshLayout.isRefreshing = false
            viewModel.refreshEarthquakes()
            initObserver()

        }

        return binding.root
    }

    private fun setStatusAndNavBarColor() {
        val window = activity?.window
        val color = SurfaceColors.SURFACE_2.getColor(requireContext())
        window!!.statusBarColor = color
        window.navigationBarColor = color
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