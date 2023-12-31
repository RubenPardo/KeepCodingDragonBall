package com.example.keepcodingdragonball.presentation.herodetails

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.keepcodingdragonball.databinding.FragmentHeroDetailsBinding
import com.example.keepcodingdragonball.domain.model.Hero
import com.example.keepcodingdragonball.domain.model.LocationModel
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


private const val ARG_PARAM1 = "hero_id"

class HeroDetailsFragment : Fragment() {

    private lateinit var heroId: String
    private lateinit var binding: FragmentHeroDetailsBinding
    private val viewModel: HeroDetailsViewModel by viewModel()

    private lateinit var lastKnownLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getString(ARG_PARAM1)?.let { hero ->
                this.heroId = hero
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeroDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservable()
        setListeners()
        viewModel.getData(heroId)
    }

    private fun setListeners() {
        binding.ivFav.setOnClickListener { viewModel.changeFavHeroById() }
    }

    private fun setObservable() {
        viewModel.heroLiveData.observe(this.viewLifecycleOwner,this::setUpViews)
        viewModel.location.observe(this.viewLifecycleOwner,this::setUpDistance)
    }

    private fun setUpDistance(text: String) = binding.run {
        tvDistance.text = text
    }

    private fun setUpViews(hero:Hero) {
        binding.tvName.text = hero.name
        binding.tvDescription.text = hero.description
        Picasso.with(context).load(hero.photo).into(binding.ivHero)
        binding.ivFav.setImageResource(if(hero.favorite) android.R.drawable.btn_star_big_on else android.R.drawable.btn_star_big_off)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(hasPermissions(context)){
            getLocation(context)
        }else{
            val permissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()){isGranted ->
                if(isGranted){
                    getLocation(context)
                }else{
                    Toast.makeText(context,"No se puede acceder a la ubicación", Toast.LENGTH_SHORT).show()
                }

            }

            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }



    @SuppressLint("MissingPermission")
    private fun getLocation(context: Context){
        val fusedLocation = LocationServices.getFusedLocationProviderClient(context)
        fusedLocation.lastLocation.addOnCompleteListener() { result ->
            if(result.isSuccessful){
                val lat = result.result?.latitude
                val long = result.result?.longitude
                if(lat!=null && long!=null) {
                    viewModel.setUserLocation(lat,long)
                }



            }
        }
    }

    private fun hasPermissions(context: Context): Boolean = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

}