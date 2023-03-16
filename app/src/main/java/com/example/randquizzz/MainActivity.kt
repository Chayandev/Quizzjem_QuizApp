package com.example.randquizzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.randquizzz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
       AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnStart.setOnClickListener{
            if(binding.editText.text!!.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Name ಠ_ಠ", Toast.LENGTH_LONG).show()
            }
            else{
                val intent=Intent(this,QuizQuistionActivity::class.java)
                intent.putExtra(Constants.USER_NAME,binding.editText.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}