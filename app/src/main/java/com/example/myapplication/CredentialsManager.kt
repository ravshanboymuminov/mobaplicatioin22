package com.example.credentials
import com.example.myapplication.MainActivity
import com.example.credentials.CredentialsManager

class CredentialsManager {


    private val users = mutableMapOf<String, String>()

    fun register(email: String, password: String): String {
        val emailLowercase = email.lowercase()

        return if (users.containsKey(emailLowercase)) {
            "Email is already taken"
        } else {

            users[emailLowercase] = password
            "Registration successful."
        }
    }


    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Validate password (should be at least 6 characters)
    fun isPasswordValid(password: String): Boolean {
        return password.length >= 6
    }

    // This method will validate the credentials (email and password)
    fun validateCredentials(email: String, password: String): Boolean {
        val emailLowercase = email.lowercase()
        return users[emailLowercase] == password
    }
}
