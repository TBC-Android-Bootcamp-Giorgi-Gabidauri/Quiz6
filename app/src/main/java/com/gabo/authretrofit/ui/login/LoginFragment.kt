package com.gabo.authretrofit.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gabo.authretrofit.base.BaseFragment
import com.gabo.authretrofit.data.models.RequestModel
import com.gabo.authretrofit.databinding.FragmentLoginBinding
import com.gabo.authretrofit.helpers.Checkers
import com.gabo.authretrofit.helpers.ResponseHandler
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(LoginViewModel::class) {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLoginBinding
        get() = FragmentLoginBinding::inflate
    private var email: String = ""
    override fun setupView(savedInstanceState: Bundle?) {
        checkLogInStatus()
        setupCheckers()
        setupClickListeners()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("requestKey") { requestKey, bundle ->
            val resultEmail = bundle.getString("EmailKey")
            val resultPassword = bundle.getString("PasswordKey")
            email = resultEmail.toString()
            binding.tietUsername.setText(resultEmail.toString())
            binding.tietPassword.setText(resultPassword.toString())
        }
    }

    private fun setupClickListeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                setupObservers()
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
            }
        }
    }

    private fun setupCheckers() {
        val checkers = Checkers(requireContext(), binding)
        var validEmail = false
        var validPassword = false
        with(binding) {
            tietUsername.doOnTextChanged { text, start, before, count ->
                validEmail = checkers.emailCheck(tietUsername, tilUsername)

            }
            tietPassword.doOnTextChanged { text, start, before, count ->
                checkers.emptyError(tietPassword, tilPassword)
                validPassword = checkers.emailCheck(tietUsername, tilUsername)
            }
            btnLogin.isClickable = validEmail && validPassword
        }
    }

    private fun setupObservers() {
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
                            login()
                            if (binding.swRememberMe.isChecked) {
                                viewModel.saveLoginStatus()
                            }
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

    private fun login() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                email
            )
        )
    }

    private fun checkLogInStatus() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginStatus.collect {
                    if (it) {
                        findNavController().navigate(
                            LoginFragmentDirections.actionLoginFragmentToHomeFragment(
                                email
                            )
                        )
                    }
                }
            }
        }
    }

}