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

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)


        credentialsManager = (activity as? MainActivity)?.credentialsManager
            ?: throw IllegalStateException("MainActivity must implement and provide credentialsManager")


        val fullNameField: EditText = view.findViewById(R.id.etFullName)
        val emailField: EditText = view.findViewById(R.id.etEmailAddress)
        val phoneField: EditText = view.findViewById(R.id.etPhoneNumber)
        val passwordField: EditText = view.findViewById(R.id.etPassword)
        val registerButton: Button = view.findViewById(R.id.submitButton)

        // Register button click listener
        registerButton.setOnClickListener {
            val fullName = fullNameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val phone = phoneField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Field validation
            when {
                email.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(activity, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                }
                !credentialsManager.isEmailValid(email) -> {
                    emailField.error = "Invalid email format"
                }
                !credentialsManager.isPasswordValid(password) -> {
                    passwordField.error = "Password must be at least 6 characters"
                }
                else -> {
                    // Register the user
                    val registrationResult = credentialsManager.register(email, password)
                    if (registrationResult == "Registration successful") {
                        Toast.makeText(activity, "Registration successful", Toast.LENGTH_SHORT).show()
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, LoginFragment())
                            .commit()
                    } else {
                        Toast.makeText(activity, registrationResult, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return view
    }
}
