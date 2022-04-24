package com.binar.challange_part5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.challange_part5.data.model.DetailMovie
import com.binar.challange_part5.data.model.GetAllMovieResponse
import com.binar.challange_part5.databinding.ItemBinding
import com.binar.challange_part5.fragment.HomeFragmentDirections
import com.bumptech.glide.Glide
import kotlinx.coroutines.GlobalScope

class Adapter(
    private val item:List<GetAllMovieResponse>
) : RecyclerView.Adapter<Adapter.MainViewHolder>() {


    class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.tvJudul.text = item[position].title
        holder.binding.tvGenre.text = item[position].overview


        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/original/"+item[position].backdropPath)
            .into(holder.binding.tvImage);


        holder.itemView.setOnClickListener {
            var judul = item[position].title
            var keterangan = item[position].overview
            var gambar = item[position].backdropPath
            var rating = item[position].voteAverage.toString()
            var detail = DetailMovie(
                judul,keterangan,gambar, rating
            )
            it.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(detail))
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}