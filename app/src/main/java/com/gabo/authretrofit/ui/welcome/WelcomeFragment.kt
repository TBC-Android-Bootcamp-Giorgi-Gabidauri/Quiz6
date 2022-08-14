package com.gabo.authretrofit.ui.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gabo.authretrofit.base.BaseFragment
import com.gabo.authretrofit.databinding.FragmentWelcomeBinding
import kotlinx.coroutines.launch

class WelcomeFragment :
    BaseFragment<WelcomeViewModel, FragmentWelcomeBinding>(WelcomeViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWelcomeBinding
        get() = FragmentWelcomeBinding::inflate

    override fun setupView(savedInstanceState: Bundle?) {
        checkLogInStatus()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
            btnLogIn.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment())
            }
        }
    }

    private fun checkLogInStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginStatus.collect {
                    if (it) {
                        findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment(null,null))
                    }
                }
            }
        }
    }


}