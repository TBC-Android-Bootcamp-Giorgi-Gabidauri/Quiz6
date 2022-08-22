package com.gabo.authretrofit.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gabo.authretrofit.base.BaseFragment
import com.gabo.authretrofit.data.models.RegisterModel
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.databinding.FragmentRegisterBinding
import com.gabo.authretrofit.helpers.Checkers
import com.gabo.authretrofit.helpers.ResponseHandler
import kotlinx.coroutines.launch

class RegisterFragment :
    BaseFragment<RegisterViewModel, FragmentRegisterBinding>(RegisterViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRegisterBinding
        get() = FragmentRegisterBinding::inflate

    override fun setupView(savedInstanceState: Bundle?) {
        setupCheckers()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            register()

            val resultEmail = binding.tietEmail.text.toString()
            val resultPassword = binding.tietPassword.text.toString()
            setFragmentResult(
                "requestKey",
                bundleOf("EmailKey" to resultEmail, "PasswordKey" to resultPassword)
            )
        }
    }

    private fun register() {
        findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
    }

    private fun setupCheckers() {
        val checkers = Checkers(requireContext(), binding)
        var validEmail = false
        var validPassword = false
        var validRepeatedPassword = false
        with(binding) {
            tietEmail.doOnTextChanged { text, start, before, count ->
                validEmail = checkers.emailCheck(tietEmail, tilEmail)
            }
            tietPassword.doOnTextChanged { text, start, before, count ->
                checkers.emptyError(tietPassword, tilPassword)
                validPassword = checkers.emptyError(tietPassword, tilPassword)
            }
            tietRepeatPassword.doOnTextChanged { text, start, before, count ->
                validRepeatedPassword = tietPassword.text == tietRepeatPassword
            }
            btnRegister.isClickable = validEmail && validPassword && validRepeatedPassword
        }
    }
}