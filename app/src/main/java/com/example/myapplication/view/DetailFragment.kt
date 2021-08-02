package com.example.myapplication.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Placeholder
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.model.Animal
import com.example.myapplication.model.AnimalPalette
import com.example.myapplication.util.getProgressDrawable
import com.example.myapplication.util.loadImage

class DetailFragment : Fragment() {

    var animal: Animal? = null
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail,
            container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            animal = DetailFragmentArgs.fromBundle(it).animal
        }
        animal?.imageUrl?.let{
            setupBackgroundColor(it)
        }
        dataBinding.animal= animal
    }

    private fun setupBackgroundColor(url: String){
        Glide.with(this).asBitmap().load(url)
            .into(object: CustomTarget<Bitmap>(){
                override fun onLoadCleared(placeholder: Drawable?){

                }
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?){
                    Palette.from(resource).generate(){ palette ->
                        val intColor = palette?.lightMutedSwatch?.rgb?:0
                        dataBinding.palette = AnimalPalette(intColor)
                    }
                }
            })
        }
}