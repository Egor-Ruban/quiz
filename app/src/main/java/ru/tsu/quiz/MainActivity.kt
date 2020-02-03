package ru.tsu.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.start_activity.*

class MainActivity : AppCompatActivity() {
    private var counter = 0
    private var numberOfQuestion = 0

    private val questions = arrayOf("как крокодил Гена искал себе друзей?",
        "как звали старуху?",
        "какую песню пел крокодил в свой день рождения?")
    private val answers = arrayOf(arrayOf("распечатал объявление о поиске друзей",
        "знакомился на улице",
        "выступал на радио с объявлением"
        ),
        arrayOf("шопоголик",
            "шапокляк",
            "шапоренко"
        ),
        arrayOf("пусть бегут неуклюже",
            "голубой вагон",
            "от улыбки хмурый день светлей"
        )
    )

    private val rightAnswers = arrayOf("распечатал объявление о поиске друзей",
        "шапокляк",
        "пусть бегут неуклюже"
    )

    private val images = arrayOf(R.drawable.gena, R.drawable.shapoklyak, R.drawable.gena_singing)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        btnStart.setOnClickListener{
            askNextQuestion()
        }

        btnClose.setOnClickListener{
            finish()
        }


    }

    private fun askNextQuestion(){
        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("question",questions[numberOfQuestion])
        intent.putExtra("answers",answers[numberOfQuestion])
        intent.putExtra("rightAnswer",rightAnswers[numberOfQuestion])
        intent.putExtra("image",images[numberOfQuestion])
        startActivityForResult(intent,1)
        numberOfQuestion++
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data?.getBooleanExtra("result",false) ?: false){
            counter++
        }

        if(numberOfQuestion==questions.size){
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("counter",counter)
            intent.putExtra("length", questions.size)
            startActivity(intent)
            finish()
        } else {
            askNextQuestion()
        }
    }
}
