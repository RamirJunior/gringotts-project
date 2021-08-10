package alura.com.gringotts.view.initial

import alura.com.gringotts.R
import alura.com.gringotts.databinding.FragmentLoginBinding
import alura.com.gringotts.presentation.initial.LoginViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.Executor

class LoginFragment : Fragment() {

    lateinit var executor: Executor
    lateinit var biometricPrompt: BiometricPrompt
    lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val loginViewModel by viewModel<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        context, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        val biometricLoginButton =
            binding.loginLogin
        biometricLoginButton.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }

        binding.loginUsername.setText(loginViewModel.getUsername())
        binding.loginPassword.setText(loginViewModel.getPassword())
        binding.loginRemember.isChecked = loginViewModel.rememberSwitch.value == true

        binding.loginUsername.addTextChangedListener {
            loginViewModel.setUsername(it.toString())
        }

        binding.loginPassword.addTextChangedListener {
            loginViewModel.setPassword(it.toString())
        }

        loginViewModel.rememberSwitch.observe(viewLifecycleOwner, {
            binding.loginRemember.isChecked = it
        })

        binding.loginRemember.setOnCheckedChangeListener { _, isChecked ->
            loginViewModel.switchChanged(isChecked)
        }

        loginViewModel.loginError.observe(viewLifecycleOwner) {
            context?.let { it1 ->
                MaterialAlertDialogBuilder(it1)
                    .setMessage(it)
                    .setPositiveButton(
                        "Ok"
                    ) { _, _ -> }
                    .show()
            }
        }

        loginViewModel.usernameError.observe(viewLifecycleOwner) {
            binding.loginUsernameLayout.error = it

        }
        loginViewModel.passwordError.observe(viewLifecycleOwner) {
            binding.loginInputLayout.error = it
        }

        loginViewModel.loginSuccess.observe(viewLifecycleOwner, {
            findNavController().navigate(R.id.action_loginFragment2_to_homeActivity)
        })

        binding.loginLogin.setOnClickListener {
            loginViewModel.onLoginButtonClicked()
        }

        loginViewModel.loading.observe(viewLifecycleOwner, {
            binding.loading.isVisible = it
            binding.loginLogin.isClickable = !it
            binding.loginRegister.isClickable = !it
        })

        binding.loginRegister.setOnClickListener {
            Toast.makeText(context, getString(R.string.tela_nao_implementada), Toast.LENGTH_LONG)
                .show()
        }

        binding.loginForgot.setOnClickListener {
            Toast.makeText(context, getString(R.string.tela_nao_implementada), Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
