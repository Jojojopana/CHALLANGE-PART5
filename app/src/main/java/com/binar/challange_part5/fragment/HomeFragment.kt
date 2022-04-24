package com.binar.challange_part5.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.challange_part5.Adapter
import com.binar.challange_part5.HomeViewModel
import com.binar.challange_part5.dao.userDB
import com.binar.challange_part5.databinding.FragmentHomeBinding
import com.binar.challange_part5.register.UserViewModel

class HomeFragment : Fragment() {
    private val sharedPref = "sharedpreferences"
    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()
    var testing: String?= null
    private var mDB: userDB?= null
    lateinit var usernameee: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = binding.root
        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDB = userDB.getInstance(requireContext())

        userViewModel.setUsername()
        fetchAllData()
        userViewModel.getUserData()

        binding.toprofile.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfile())
        }
        username()
        Log.d("test",userViewModel.Username.toString())
    }

    private fun username() {
        userViewModel.nama.observe(viewLifecycleOwner){
            binding.username.text = it
            Log.d("nama", "$it")
        }
    }

    private fun fetchAllData() {
        Log.d("Tag","Fragment activity : datanya ->")
        viewModel.getMovies().observe(requireActivity()){
            Log.d("Tag","Fragment activity : datanya -> $it")
            val adapter = Adapter(it)
            val layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
            binding.userRecyclerView.layoutManager = layoutManager
            binding.userRecyclerView.adapter = adapter
        }
    }
}