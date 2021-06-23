package com.android.pickarestaurant.screens.loading

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.pickarestaurant.R
import com.android.pickarestaurant.databinding.FragmentLoadingBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import java.util.concurrent.TimeUnit


class LoadingFragment : Fragment() {

    private lateinit var viewModel: LoadingViewModel

    private lateinit var binding: FragmentLoadingBinding

    // FusedLocationProviderClient - Main class for receiving location updates.
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var locationRequest: LocationRequest
    private val PERMISSION_FINE_LOCATION: Int = 99

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_loading, container, false
        )

        viewModel = ViewModelProvider(this).get(LoadingViewModel::class.java)

        binding.loadingViewModel = viewModel

        // Specify the current activity as the lifecycle owner of the binding. This is used so that
        // the binding can observe LiveData updates
        binding.lifecycleOwner = this

        viewModel.selectedRestaurant.observe(this, Observer {
            if (null != it) {
                findNavController().navigate(LoadingFragmentDirections.actionLoadingFragmentToResultFragment(it))
                viewModel.displayRestaurantComplete()
            }
        })

        startLocationUpdates()

        return binding.root
    }

    private fun startLocationUpdates() {
        locationRequest = LocationRequest.create().apply {
            // Sets the interval for active location updates.
            interval = TimeUnit.SECONDS.toMillis(60)

            // Sets the fastest rate for active location updates.
            fastestInterval = TimeUnit.SECONDS.toMillis(30)

            // Sets the maximum time when batched location updates are delivered.
            maxWaitTime = TimeUnit.MINUTES.toMillis(2)

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(activity as Context)

        if (ActivityCompat.checkSelfPermission(
                (activity as Context),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                viewModel.latitude.value = location.latitude.toString()
                viewModel.longitude.value = location.longitude.toString()
                viewModel.LaunchGoogleSearch()
            }
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_FINE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_FINE_LOCATION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates()
                } else {
                    Toast.makeText(activity as Context?, "This app requires permission to be granted in order to work properly", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
