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
import com.example.credentials.CredentialsManager  // Ensure CredentialsManager is correctly imported
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

    private val credentialsManager = CredentialsManager()  // Initialize CredentialsManager

    // Views
    private val emailInput: EditText by lazy { findViewById(R.id.emailInput) }
    private val passwordInput: EditText by lazy { findViewById(R.id.passwordInput) }
    private val nextButton: Button by lazy { findViewById(R.id.nextButton) }
    private val rememberMeCheckBox: CheckBox by lazy { findViewById(R.id.rememberMeCheckBox) }
    private val emailLayout: TextInputLayout by lazy { findViewById(R.id.emailInputLayout) }
    private val passwordLayout: TextInputLayout by lazy { findViewById(R.id.passwordInputLayout) }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loginlay)

        // Handle "Next" button click
        nextButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Validate inputs
            when {
                email.isEmpty() -> {
                    emailLayout.error = "Email is required"
                }
                !credentialsManager.isEmailValid(email) -> {
                    emailLayout.error = "Invalid email format"
                }
                password.isEmpty() -> {
                    passwordLayout.error = "Password is required"
                }
                password.length < 6 -> {
                    passwordLayout.error = "Password must be at least 6 characters"
                }
                else -> {
                    // Hardcoded credentials login check
                    if (email == "test@te.st" && password == "1234") {
                        // Credentials are valid
                        Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()

                        // Navigate to MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()  // Close LoginActivity
                    } else {
                        // Invalid credentials
                        Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        // Handle "Register Now" text click
        val registerText = findViewById<Button>(R.id.registerNowText)
        registerText.setOnClickListener {
            val intent = Intent(this, RegisterlayActivity::class.java)
            startActivity(intent)
        }
    }
}
