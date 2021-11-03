package com.example.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.app.databinding.ActivityMainBinding
import com.example.app.fragments.ScrollFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //hides action bar
        supportActionBar!!.hide()

        //gets view binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //sets initial fragment
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<ScrollFragment>(R.id.fragment_container)
            }
        }

        // Hello world

    }


}
