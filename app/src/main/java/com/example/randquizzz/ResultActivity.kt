package com.example.randquizzz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randquizzz.databinding.ActivityResultBinding
import kotlin.system.exitProcess

class ResultActivity : AppCompatActivity() {
    private lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding=ActivityResultBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.scoreCard.setBackgroundResource(R.drawable.congratulation)
        binding.name.text="Hay ${intent.getStringExtra(Constants.USER_NAME)}"
        val totalCorrectAns=intent.getIntExtra(Constants.CORRECT_ANSWER,0)
        val totalQuestions=intent.getIntExtra(Constants.TOTAL_QUESTIONS,0)
        binding.resultComment.text="$totalCorrectAns out of $totalQuestions"
        binding.score.text="${totalCorrectAns*10}"
        binding.btnfinish.setOnClickListener{
           startActivity(Intent(this,MainActivity::class.java))
        }
        binding.btnLeave.setOnClickListener{
            finish()
            exitProcess(0)
        }

    }
}