package com.aayar94.earthquakes.ui.fragment.MapsFragment

import android.os.Bundle
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

class MapsFragment : Fragment() {
    private var mBinding: FragmentMapsBinding? = null
    private val binding get() = mBinding!!

    val args: MapsFragmentArgs by navArgs()

    private val callback = OnMapReadyCallback { googleMap ->
        val location = LatLng(args.earthquakeModel.lat ?: 0.0, args.earthquakeModel.lng ?: 0.0)
        val marker =
            googleMap.addMarker(MarkerOptions().position(location).title(args.earthquakeModel.name))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 8f))
        marker?.showInfoWindow()
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMapsBinding.inflate(layoutInflater, container, false)
        setDetailInfo()
        return binding.root
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
                getText(R.string.depth).toString() + this@MapsFragment.args.earthquakeModel.depth.toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}