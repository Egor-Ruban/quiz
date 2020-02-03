package ru.tsu.quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.start_activity.*

class MainActivity : AppCompatActivity() {
    private var rightAnswered = 0
    private var asked = 0

    private var questions = arrayOf<String>()
    private var answers = arrayOf<Array<String>>()
    private var rightAnswers = arrayOf<String>()
    private val images = arrayOf(R.drawable.gena, R.drawable.shapoklyak, R.drawable.gena_singing)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)

        questions = resources.getStringArray(R.array.questions)
        answers = arrayOf(
            resources.getStringArray(R.array.answers_1),
            resources.getStringArray(R.array.answers_2),
            resources.getStringArray(R.array.answers_3)
        )
        rightAnswers = resources.getStringArray(R.array.rightAnswers)

        btnStart.setOnClickListener {
            askNextQuestion()
        }

        btnClose.setOnClickListener {
            finish()
        }
    }

    private fun askNextQuestion() {
        val intent = Intent(this, QuestionActivity::class.java)
        intent.putExtra("question", questions[asked])
        intent.putExtra("answers", answers[asked])
        intent.putExtra("rightAnswer", rightAnswers[asked])
        intent.putExtra("image", images[asked])
        startActivityForResult(intent, 1)
        asked++
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data?.getBooleanExtra("result", false) == true) {
            rightAnswered++
        }

        if (asked == questions.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("counter", rightAnswered)
            intent.putExtra("length", questions.size)
            startActivity(intent)
            finish()
        } else {
            askNextQuestion()
        }
    }
}
