package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.credentials.CredentialsManager
import com.example.myapplication.MainActivity
import com.example.assignments.R

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Access credentialsManager from MainActivity
        credentialsManager = (activity as MainActivity).credentialsManager

        val emailField: EditText = view.findViewById(R.id.emailInput)
        val passwordField: EditText = view.findViewById(R.id.passwordInput)
        val loginButton: Button = view.findViewById(R.id.nextButton)

        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            when {
                email.isEmpty() -> emailField.error = "Email is required"
                !credentialsManager.isEmailValid(email) -> emailField.error = "Invalid email format"
                password.isEmpty() -> passwordField.error = "Password is required"
                password.length < 6 -> passwordField.error = "Password must be at least 6 characters"
                else -> {
                    if (credentialsManager.validateCredentials(email, password)) {
                        Toast.makeText(activity, "Signed in successfully", Toast.LENGTH_SHORT).show()

                        Toast.makeText(activity, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }
}
