package com.example.unimap.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.unimap.R
import com.example.unimap.places.Place
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.codelabs.buildyourfirstmap.place.PlacesReader


class MapFragment : Fragment() {
    private val places: List<Place> by lazy {
        PlacesReader(this.requireContext()).read()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mapFragment = (activity as FragmentActivity).supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync { googleMap ->

            googleMap.setOnMapLoadedCallback {
                val bounds = LatLngBounds.builder()
                places.forEach { bounds.include(it.latLng) }
//                val cameraPosition = CameraPosition.builder()
//                    .target(LatLng(53.0091,2.1761))
//                    .zoom(16f)
//                    .bearing(0f)
//                    .tilt(45f)
//                    .build()
//                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 20))
            }
            addMarkers(googleMap)
        }
        return inflater.inflate(R.layout.fragment_map, container, false)

    }
    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
            )
        }
    }
}
