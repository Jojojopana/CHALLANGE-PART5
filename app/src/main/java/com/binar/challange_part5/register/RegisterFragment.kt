package com.binar.challange_part5.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.binar.challange_part5.User
import com.binar.challange_part5.dao.userDB
import com.binar.challange_part5.databinding.FragmentRegisterBinding
import java.util.concurrent.Executors

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private var mDB: userDB?= null
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = binding.root
        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mDB = userDB.getInstance(requireContext())
        Executors.newSingleThreadExecutor()
        val executor = Executors.newSingleThreadExecutor()
        binding.btnRegister.setOnClickListener{
            val confPassword = binding.usernameRegister.text.toString()
            val objectUser = User(
                userName = binding.usernameRegister.text.toString(),
                password = binding.passwordRegister.text.toString()
            )
            viewModel.registerUser(objectUser,confPassword)
        }
        navigateUi()
    }
    fun navigateUi() {
        viewModel.registervalidation.observe(viewLifecycleOwner){
            if(it!=0){
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                Log.d("register","berhasil $it")
            } else {
                Log.d("register","test $it")
            }
        }
    }
}