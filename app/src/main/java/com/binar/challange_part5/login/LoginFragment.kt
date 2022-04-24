package com.binar.challange_part5.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.binar.challange_part5.R
import com.binar.challange_part5.databinding.FragmentLoginBinding
import com.binar.challange_part5.databinding.FragmentRegisterBinding
import com.binar.challange_part5.register.RegisterFragmentDirections
import com.binar.challange_part5.register.UserViewModel


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        val view = binding.root
        return (view)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.logincheck()
        binding.tombolLogin.setOnClickListener{
            val usernameinput = binding.usernameInput.text.toString()
            val passwordinput = binding.passwordInput.text.toString()

            viewModel.loginUser(usernameinput,passwordinput)
        }
        binding.toregister.setOnClickListener{
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        navigateUi()
    }

    fun navigateUi() {
        viewModel.validation.observe(viewLifecycleOwner){
            if(it!=0){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                Log.d("login","berhasil $it")
            } else {
                Log.d("login","test $it")
            }
        }
    }
}