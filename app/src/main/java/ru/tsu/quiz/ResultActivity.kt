package ru.tsu.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.result_activity.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)

        val answered = intent.getIntExtra("counter", 0)
        val total = intent.getIntExtra("length", 0)

        tvResultCounter.text = "$answered/$total"

        val eq : Float = answered.toFloat()/total
        tvResult.text = when(eq){
            in 0F..0.3F -> resources.getString(R.string.quizFailed)
            in 0.3F..0.7F -> resources.getString(R.string.quizOK)
            in 0.7F..1F -> resources.getString(R.string.quizGreat)
            else -> "what are you"
        }

        btnResultClose.setOnClickListener {
            finish()
        }

        btnResultRestart.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}