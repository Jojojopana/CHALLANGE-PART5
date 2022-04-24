package com.binar.challange_part5.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.binar.challange_part5.R
import com.binar.challange_part5.databinding.FragmentDetailBinding
import com.binar.challange_part5.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import kotlinx.android.extensions.CacheImplementation

class DetailFragment : Fragment() {
    private val arguments: DetailFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = binding.root
        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       var judul = arguments.detail.title
        var keterangan = arguments.detail.keterangan
        var image = arguments.detail.image
        var rating = arguments.detail.rating

        with(binding) {

            judulDetail.setText(judul)
            keteranganDetail.setText(keterangan)
            ratingDetail.setText(rating)

            Glide.with(requireContext())
                .load("https://image.tmdb.org/t/p/original/"+image)
                .into(binding.gambar);
        }
    }
}