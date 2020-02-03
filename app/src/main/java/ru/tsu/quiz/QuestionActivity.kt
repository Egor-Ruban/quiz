package ru.tsu.quiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_question.*

class QuestionActivity : AppCompatActivity(){

    lateinit var rightAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val question = intent.getStringExtra("question") ?: "question went wrong"
        val answers = intent.getStringArrayExtra("answers") ?: arrayOf("first","second","third")
        val image = intent.getIntExtra("image", R.drawable.gena)
        rightAnswer = intent.getStringExtra("rightAnswer") ?: "something went wrong"

        tvQuestion.text = question
        ivQuestion.setImageResource(image)
        btnQuestionFirst.text = answers[0]
        btnQuestionFirst.setOnClickListener{
            onAnswer(it)
        }
        btnQuestionSecond.text = answers[1]
        btnQuestionSecond.setOnClickListener{
            onAnswer(it)
        }
        btnQuestionThird.text = answers[2]
        btnQuestionThird.setOnClickListener{
            onAnswer(it)
        }
    }

    private fun onAnswer(v: View) {
        val intent = Intent()

        if ((v as TextView).text == rightAnswer) {
            intent.putExtra("result",true)
        } else {
            intent.putExtra("result",false)
        }
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}