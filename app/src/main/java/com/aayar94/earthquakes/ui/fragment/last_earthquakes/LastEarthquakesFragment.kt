package com.aayar94.earthquakes.ui.fragment.last_earthquakes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.aayar94.earthquakes.R
import com.aayar94.earthquakes.databinding.FragmentLastEarthquakesBinding
import com.google.android.material.elevation.SurfaceColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LastEarthquakesFragment : Fragment(), SearchView.OnQueryTextListener {
    private var mBinding: FragmentLastEarthquakesBinding? = null
    private val binding get() = mBinding!!

    private val mAdapter: AdapterLastEarthquakesRV by lazy {
        AdapterLastEarthquakesRV()
    }

    /**{
    val action =
    LastEarthquakesFragmentDirections.actionLastEarthquakesFragmentToMapsFragment(it)
    findNavController().navigate(action)
    }
    }*/
    val viewModel: LastEarthquakesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentLastEarthquakesBinding.inflate(layoutInflater)
        setupMenu()
        setupRecyclerView()
        initObserver()
        setStatusAndNavBarColor()

        binding.swipeToRefreshLayout.setOnRefreshListener {
            binding.swipeToRefreshLayout.isRefreshing = false
            viewModel.refreshEarthquakes()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getEarthquakes()
        postponeEnterTransition()
        binding.earthquakesRV.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_lastearthquakes_fragment, menu)
                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this@LastEarthquakesFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    androidx.appcompat.R.id.home -> {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                    }

                    R.id.menu_sort_highMag -> viewModel.sortHighMag()
                    R.id.menu_sort_lowMag -> viewModel.sortLowMag()
                    R.id.menu_sort_normal -> viewModel.refreshEarthquakes()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }


    private fun setStatusAndNavBarColor() {
        val window = activity?.window
        val color = SurfaceColors.SURFACE_2.getColor(requireContext())
        window!!.statusBarColor = color
        window.navigationBarColor = color
    }


    private fun setupRecyclerView() {
        binding.earthquakesRV.adapter = mAdapter
    }

    private fun initObserver() {
        viewModel.earthquakes.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                binding.earthquakesRV.visibility = View.INVISIBLE
                binding.noDataIV.visibility = View.VISIBLE
                binding.nodataTV.visibility = View.VISIBLE

            } else {
                binding.earthquakesRV.visibility = View.VISIBLE
                binding.noDataIV.visibility = View.INVISIBLE
                binding.nodataTV.visibility = View.INVISIBLE

            }
            mAdapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            viewModel.searchEarthquake(query)
        }
        return true
    }
}