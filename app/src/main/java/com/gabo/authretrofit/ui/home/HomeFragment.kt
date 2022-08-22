package com.gabo.authretrofit.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gabo.authretrofit.R
import com.gabo.authretrofit.base.BaseFragment
import com.gabo.authretrofit.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(HomeViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    private val args: HomeFragmentArgs by navArgs()

    @SuppressLint("SetTextI18n")
    override fun setupView(savedInstanceState: Bundle?) {
        setupClickListeners()
        args.email.let {
            binding.tvHome.text = "email: $it"
        }
    }

    private fun setupClickListeners(){
        binding.btnLogOut.setOnClickListener {
            viewModel.deleteLoginStatus()
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }
}