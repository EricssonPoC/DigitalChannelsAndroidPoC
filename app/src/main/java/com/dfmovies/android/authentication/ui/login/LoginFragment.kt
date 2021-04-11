package com.dfmovies.android.authentication.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.dfmovies.android.R
import com.dfmovies.android.core.ui.BaseFragment
import com.dfmovies.android.core.util.extension.observeNonNull
import com.dfmovies.android.databinding.FragmentLoginBinding
import com.dfmovies.android.main.ui.MainActivity
import com.dfmovies.android.main.ui.search.SearchFragmentViewModel
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel: LoginViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getActivityViewModelProvider().get(LoginViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun getScreeningPage(): String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        with(viewModel) {
            getAllInputsAreValidLiveData().observeNonNull(viewLifecycleOwner) {
                startActivity(MainActivity.newIntent(requireContext()))
            }

            getShowEmailValidationErrorLiveData().observeNonNull(viewLifecycleOwner) {
                showEmailError(getString(R.string.error_email_text))
            }

            getShowPasswordEmptyErrorLiveData().observeNonNull(viewLifecycleOwner) {
                showPasswordError(getString(R.string.error_password_text))
            }
        }
    }

    private fun setUpView() {
        with(binding) {
            buttonLogin.setOnClickListener {
                viewModel.login(
                    email = editTextEmail.text.toString(),
                    password = editTextPassword.text.toString()
                )
            }

            editTextEmail.addTextChangedListener(
                afterTextChanged = { hideEmailError() }
            )

            editTextPassword.addTextChangedListener(
                afterTextChanged = { hidePasswordError() }
            )
        }
    }

    private fun showPasswordError(error: String) {
        binding.inputLayoutPassword.error = error
    }

    private fun hidePasswordError() {
        binding.inputLayoutPassword.error = ""
    }

    private fun showEmailError(error: String) {
        binding.inputLayoutEmail.error = error
    }

    private fun hideEmailError() {
        binding.inputLayoutEmail.error = ""
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}