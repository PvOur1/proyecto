package com.example.taller2.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.taller2.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class TiendaFragment : Fragment(), OnMapReadyCallback {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tiendas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa el cliente de ubicación
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        // Encuentra el fragmento del mapa
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        googleMap.uiSettings.isZoomControlsEnabled = true  // Muestra botones + y -
        googleMap.uiSettings.isZoomGesturesEnabled = true  // Permite hacer zoom con gesto
        googleMap.uiSettings.isScrollGesturesEnabled = true // Permite mover el mapa
        googleMap.uiSettings.isRotateGesturesEnabled = true // Permite rotar el mapa
        googleMap.uiSettings.isTiltGesturesEnabled = true   // Permite inclinar la vista

        // Verifica el permiso de ubicación
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        }

        googleMap.isMyLocationEnabled = true

        // Obtiene la última ubicación conocida del usuario
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val userLatLng = LatLng(location.latitude, location.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 14f))
                googleMap.addMarker(MarkerOptions().position(userLatLng).title("Tu ubicación"))
            }
        }

        // Marcadores personalizados (puntos de tiendas)
        val puntosTiendas = listOf(
            LatLng(4.60971, -74.08175),  // Bogotá
            LatLng(6.2442, -75.5812),    // Medellín
            LatLng(3.4516, -76.5320)     // Cali
        )

        val nombresTiendas = listOf("Tienda Bogotá", "Tienda Medellín", "Tienda Cali")

        for (i in puntosTiendas.indices) {
            googleMap.addMarker(
                MarkerOptions()
                    .position(puntosTiendas[i])
                    .title(nombresTiendas[i])
            )
        }
    }
}
