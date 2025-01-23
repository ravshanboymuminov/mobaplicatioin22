package com.example.credentials

import org.junit.Assert.*
import org.junit.Test

class CredentialsManagerTest {

    private val credentialsManager = CredentialsManager()


    @Test
    fun `test successful registration`() {
        val email = "test@example.com"
        val password = "password123"

        val result = credentialsManager.register(email, password)

        assertEquals("Registration successful.", result)
    }


    @Test
    fun `test registration with already taken email (case insensitive)`() {
        val email = "test@example.com"
        val password = "password123"

        // First registration
        credentialsManager.register(email, password)


        val result = credentialsManager.register("TEST@example.com", "newpassword")

        assertEquals("Error: Email is already taken.", result)
    }


    @Test
    fun `test case insensitivity on registration`() {
        val email = "test@example.com"
        val password = "password123"

        credentialsManager.register(email, password)


        assertTrue(credentialsManager.validateCredentials("TEST@example.com", password))
    }


    @Test
    fun `test invalid email format`() {
        val email = "invalid-email"
        val password = "password123"

        val result = credentialsManager.register(email, password)

        assertEquals("Error: Email is already taken.", result)
    }
}
