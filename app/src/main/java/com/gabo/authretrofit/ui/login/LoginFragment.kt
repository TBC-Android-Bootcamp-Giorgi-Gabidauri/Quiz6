package com.gabo.authretrofit.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gabo.authretrofit.base.BaseFragment
import com.gabo.authretrofit.data.models.LoginModel
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.databinding.FragmentLoginBinding
import com.gabo.authretrofit.helpers.ResponseHandler
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate

    override fun setupView(savedInstanceState: Bundle?) {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            setupObservers()
        }
    }

    private fun setupObservers() {
        with(binding) {
            when {
                tietUsername.text.toString().isEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        "Email field must not be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                tietPassword.text.toString().isEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        "Password field must not be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            viewModel.requestLogin(
                                RequestModel(
                                    binding.tietUsername.text.toString(),
                                    binding.tietPassword.text.toString()
                                )
                            ).collect {
                                when (it) {
                                    is ResponseHandler.Success -> {
                                        login(it.data!!)
                                        viewModel.saveLoginStatus()
                                    }
                                    is ResponseHandler.Error -> {
                                        Toast.makeText(
                                            requireContext(),
                                            it.errorMSg,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun login(model: LoginModel) {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                loginModel = model,
                registerModel = null
            )
        )
    }


}