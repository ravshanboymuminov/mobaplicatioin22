package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignments.R
import com.example.credentials.CredentialsManager  // Ensure this import matches your project structure

class RegisterlayActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()  // Initialize CredentialsManager

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrationlay)

        val fullNameField = findViewById<EditText>(R.id.etFullName)
        val emailField = findViewById<EditText>(R.id.etEmailAddress)
        val phoneField = findViewById<EditText>(R.id.etPhoneNumber)
        val passwordField = findViewById<EditText>(R.id.etPassword)
        val termsCheckBox = findViewById<CheckBox>(R.id.agreementCheckbox)
        val registerButton = findViewById<Button>(R.id.submitButton)

        registerButton.setOnClickListener {
            val fullName = fullNameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val phone = phoneField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            // Validate inputs
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!credentialsManager.isEmailValid(email)) {
                emailField.error = "Invalid email format"
                return@setOnClickListener
            }

            if (!credentialsManager.isPasswordValid(password)) {
                passwordField.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            // Check if the user agreed to the terms and conditions
            if (!termsCheckBox.isChecked) {
                Toast.makeText(this, "You must agree to the terms and conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register the user
            val registrationResult = credentialsManager.register(email, password)

            if (registrationResult == "Registration successful.") {
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()

                // Navigate to LoginActivity after successful registration
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()  // Close RegisterlayActivity
            } else {
                // Show error if the email is already taken
                Toast.makeText(this, registrationResult, Toast.LENGTH_SHORT).show()
            }
        }

        // Go back to LoginActivity if already registered
        val loginText = findViewById<Button>(R.id.login)
        loginText.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Close RegisterActivity
        }
    }
}
