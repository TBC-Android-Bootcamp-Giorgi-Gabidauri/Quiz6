package com.gabo.authretrofit.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gabo.authretrofit.base.BaseFragment
import com.gabo.authretrofit.data.models.RegisterModel
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.databinding.FragmentRegisterBinding
import com.gabo.authretrofit.helpers.ResponseHandler
import kotlinx.coroutines.launch

class RegisterFragment :
    BaseFragment<RegisterViewModel, FragmentRegisterBinding>(RegisterViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setupView(savedInstanceState: Bundle?) {
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            setupObservers()
        }
    }

    private fun register(model: RegisterModel) {
        findNavController().navigate(
            RegisterFragmentDirections.actionRegisterFragmentToHomeFragment(
                registerModel = model,
                loginModel = null
            )
        )
    }

    private fun setupObservers() {
        with(binding) {
            when {
                tietEmail.text.toString().isEmpty() -> {
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

                tietUsername.text.toString().isEmpty() -> {
                    Toast.makeText(
                        requireContext(),
                        "Username field must not be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    viewLifecycleOwner.lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            viewModel.requestRegister(
                                RequestModel(
                                    binding.tietEmail.text.toString(),
                                    binding.tietPassword.text.toString()
                                )
                            ).collect {
                                when (it) {
                                    is ResponseHandler.Success -> {
                                        register(it.data!!)
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

}