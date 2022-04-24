package com.binar.challange_part5.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.binar.challange_part5.R
import com.binar.challange_part5.User
import com.binar.challange_part5.dao.userDB
import com.binar.challange_part5.databinding.FragmentHomeBinding
import com.binar.challange_part5.databinding.FragmentProfileBinding
import com.binar.challange_part5.register.UserViewModel
import java.util.*


class Profile : Fragment() {
    lateinit var binding: FragmentProfileBinding
    private val viewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = binding.root
        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.setUsername()

        binding.updateprofile.setOnClickListener{
            val input = User(
                userName = binding.profileusername.text.toString()
            )
            viewModel.updateUserData(input)
            findNavController().navigate(ProfileDirections.actionProfileToHomeFragment())
        }

        binding.logoutprofile.setOnClickListener{
            viewModel.logout()
            findNavController().navigate(ProfileDirections.actionProfileToLoginFragment())
        }
    }
}