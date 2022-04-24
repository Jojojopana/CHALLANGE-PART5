package com.binar.challange_part5

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.challange_part5.data.model.GetAllMovieResponse
import com.binar.challange_part5.data.model.MovieModel
import com.binar.challange_part5.data.network.MovieApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    private val movies : MutableLiveData<List<GetAllMovieResponse>> by lazy {
        MutableLiveData<List<GetAllMovieResponse>>().also {
            getAllMovies()
        }
}

    fun getMovies(): LiveData<List<GetAllMovieResponse>>{
        return movies
    }
    private fun getAllMovies(){
        MovieApi.retrofitService.AllMovie().enqueue(object : retrofit2.Callback<MovieModel>{
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                movies.value = response.body()?.results
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.d("Tag",t.message.toString())
            }

        })
    }
}