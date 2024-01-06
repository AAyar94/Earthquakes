package com.aayar94.earthquakes.ui.fragment.maps_fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aayar94.earthquakes.R
import com.aayar94.earthquakes.databinding.FragmentMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.elevation.SurfaceColors

class MapsFragment : Fragment() {
    private var mBinding: FragmentMapsBinding? = null
    private val binding get() = mBinding!!

    val args: MapsFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        val coordinates = args.earthquakeModel.geoJson.coordinates

        val location = LatLng(coordinates[0], coordinates[1])
        val marker =
            googleMap.addMarker(MarkerOptions().position(location).title(args.earthquakeModel.name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 7f))
        marker?.showInfoWindow()
        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMapsBinding.inflate(layoutInflater, container, false)
        setDetailInfo()
        setStatusAndNavBarColor()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        postponeEnterTransition()
        startPostponedEnterTransition()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    private fun setDetailInfo() {
        with(binding.include) {
            txtMag.text = args.earthquakeModel.magnitudeText
            txtName.text = args.earthquakeModel.name
            txtDate.text = args.earthquakeModel.date
            txtTime.text = args.earthquakeModel.time
            cardMag.setCardBackgroundColor(args.earthquakeModel.magnitudeColor)
            rootCardView.setCardBackgroundColor(args.earthquakeModel.magnitudeColorLight)
            txtDepth.text =
                "${getText(R.string.depth)}${this@MapsFragment.args.earthquakeModel.depth.toString()}"
            binding.include.root.transitionName = "CardViewTransition"
        }
    }

    private fun setStatusAndNavBarColor() {
        val window = activity?.window
        val color = SurfaceColors.SURFACE_2.getColor(requireContext())
        window!!.statusBarColor = color
        window.navigationBarColor = color
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}