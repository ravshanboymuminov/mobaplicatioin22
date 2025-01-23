package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.fragments.FragmentA  // Import FragmentA
import com.example.myapplication.fragments.FragmentB  // Import FragmentB
import com.example.assignments.R

class MainActivity : AppCompatActivity() {

    private lateinit var toggleFragmentButton: Button
    private var isFragmentAVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toggleFragmentButton = findViewById(R.id.toggleFragmentButton)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentA())  // Replace with FragmentA
                .commit()
        }


        toggleFragmentButton.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            if (isFragmentAVisible) {
                transaction.replace(R.id.fragment_container, FragmentB()) // Replace with FragmentB
            } else {
                transaction.replace(R.id.fragment_container, FragmentA()) // Replace with FragmentA
            }
            transaction.commit() // Commit the transaction

            isFragmentAVisible = !isFragmentAVisible // Toggle the state
        }
    }
}
