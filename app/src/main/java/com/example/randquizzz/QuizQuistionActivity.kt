package com.example.randquizzz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.icu.text.TimeZoneFormat.TimeType
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.core.content.ContextCompat
import com.example.randquizzz.databinding.ActivityMainBinding
import com.example.randquizzz.databinding.ActivityQuizQuistionBinding
import java.util.zip.Inflater

class QuizQuistionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizQuistionBinding
    private var myCurrentPosition: Int = 1
    private var mSelectedOption: Int = 0
    private var questionsList: ArrayList<Question>? = null
    private var userName: String? = null
    private var correctAnswers: Int = 0
    private var tempPrevious: Int = -1
    private var tempPreviousWrong: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuistionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        questionsList = Constants.getQuestions()
        binding.opt1Layout.setOnClickListener(this)
        binding.opt2Layout.setOnClickListener(this)
        binding.opt3Layout.setOnClickListener(this)
        binding.opt4Layout.setOnClickListener(this)
        binding.btnNxt.setOnClickListener(this)
        //  binding.btnPrev.setOnClickListener(this)
        setQuestion(questionsList!!)
        userName = intent.getStringExtra(Constants.USER_NAME)
    }

    //assignment
    private fun setQuestion(questionsList: ArrayList<Question>) {
        defaultOptionView()
        val question: Question = questionsList[myCurrentPosition - 1]
        Log.e("1st Question:", question.question)
        binding.questionNo.text = "Question:$myCurrentPosition"
        binding.progressBar.progress = myCurrentPosition
        binding.tvPrpogress.text = "$myCurrentPosition/${binding.progressBar.max}"
        binding.question.text = question.question
        binding.flagImage.setImageResource(question.image)
        binding.opt1.text = question.optionOne
        binding.opt2.text = question.optionTwo
        binding.opt3.text = question.optionThree
        binding.opt4.text = question.optionFour

        if (myCurrentPosition == questionsList.size) {
            binding.btnNxt.text = "FINISH"
        } else {
            binding.btnNxt.text = "SUBMIT"
        }
    }

    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        val optionLayout = ArrayList<LinearLayout>()
        binding.opt1.let {
            options.add(0, it)
        }
        binding.opt2.let {
            options.add(1, it)
        }
        binding.opt3.let {
            options.add(2, it)
        }
        binding.opt4.let {
            options.add(3, it)
        }
        binding.opt1Layout.let {
            optionLayout.add(0, it)
        }
        binding.opt2Layout.let {
            optionLayout.add(1, it)
        }
        binding.opt3Layout.let {
            optionLayout.add(2, it)
        }
        binding.opt4Layout.let {
            optionLayout.add(3, it)
        }
        for (option in options) {
            option.setTextColor(Color.parseColor("#d2d0f2"))
            option.setTypeface(option.typeface, Typeface.NORMAL)
        }
        for (layout in optionLayout) {
            layout.background = ContextCompat.getDrawable(
                this, R.drawable.custom_optionshape
            )
        }
    }

    private fun selectedOptionView(tv: TextView, layOut: LinearLayout, selectedOptionIndx: Int) {
        defaultOptionView()
        binding.btnNxt.text = "SUBMIT"
        mSelectedOption = selectedOptionIndx
        tv.setTextColor(Color.parseColor("#FFFFFFFF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        layOut.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(option: View?) {
        when (option?.id) {
            R.id.opt1Layout -> selectedOptionView(binding.opt1, binding.opt1Layout, 1)
            R.id.opt2Layout -> selectedOptionView(binding.opt2, binding.opt2Layout, 2)
            R.id.opt3Layout -> selectedOptionView(binding.opt3, binding.opt3Layout, 3)
            R.id.opt4Layout -> selectedOptionView(binding.opt4, binding.opt4Layout, 4)
            R.id.btnNxt -> {
                if (mSelectedOption == 0) {
                    if (binding.btnNxt.text == "NEXT" || binding.btnNxt.text == "FINISH")
                        myCurrentPosition++
                    else
                        Toast.makeText(this, "Answer 👆this to continue", Toast.LENGTH_SHORT).show()


                    when {
                        myCurrentPosition <= questionsList!!.size -> {
                            setQuestion(questionsList!!)
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, userName)
                            intent.putExtra(Constants.CORRECT_ANSWER, correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = questionsList!![myCurrentPosition - 1]
                    Log.e("correct:", "${question?.correctAnswer}")
                    Log.e("correct:", "$mSelectedOption")
                    // tempPrevious=question.id
                    if (question!!.correctAnswer != mSelectedOption) {
                        answerView(mSelectedOption, R.drawable.wrong_optionshape)
                        tempPreviousWrong = question.id
                        tempPrevious=question.id
                    } else {
                        if (correctAnswers < questionsList!!.size) {
                            if (question.id != tempPrevious)
                                correctAnswers++
                            else if (question.id == tempPreviousWrong)
                                Toast.makeText(
                                    this,
                                    "This submission is correct but will not be counted!!",
                                    Toast.LENGTH_LONG
                                ).show()
                            else
                                Toast.makeText(
                                    this,
                                    "You can get marks once for correct submission!",
                                    Toast.LENGTH_LONG
                                ).show()
                        }
                        tempPrevious = question.id
                    }
                    answerView(question!!.correctAnswer, R.drawable.correct_optionshape)
                    when (question!!.correctAnswer) {
                        1 -> binding.opt1.setTextColor(Color.parseColor("#FF000000"))
                        2 -> binding.opt2.setTextColor(Color.parseColor("#FF000000"))
                        3 -> binding.opt3.setTextColor(Color.parseColor("#FF000000"))
                        4 -> binding.opt4.setTextColor(Color.parseColor("#FF000000"))

                    }
                    if (myCurrentPosition == questionsList!!.size)
                        binding.btnNxt.text = "FINISH"
                    else
                        binding.btnNxt.text = "NEXT"

                    mSelectedOption = 0
                }
            }
//            R.id.btnPrev->{
//                if(myCurrentPosition!=1) {
//                    myCurrentPosition--
//                    when {
//                        myCurrentPosition <= questionsList!!.size -> {
//                            setQuestion(questionsList!!)
//                        }
//                    }
//                }
//                else{
//                    Toast.makeText(this,"Nothing in previous",Toast.LENGTH_LONG).show()
//                }
//            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> binding.opt1Layout.background = ContextCompat.getDrawable(this, drawableView)
            2 -> binding.opt2Layout.background = ContextCompat.getDrawable(this, drawableView)
            3 -> binding.opt3Layout.background = ContextCompat.getDrawable(this, drawableView)
            4 -> binding.opt4Layout.background = ContextCompat.getDrawable(this, drawableView)
        }
    }


}