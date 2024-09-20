package acosta.alonso.quizzapp

import acosta.alonso.quizzapp.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    lateinit var truebutton: Button
    lateinit var falsebutton: Button
   /* private val questionBank = listOf(
        Question(R.string.pregunta_cielo, false),
        Question(R.string.pregunta_subiarri, false),
        Question(R.string.pregunta_murcielago, true),
        Question(R.string.pregunta_TF, true),
        Question(R.string.pregunta_DB, false)
    )
    private var currentIndex = 0*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        truebutton = findViewById(R.id.true_button)
        falsebutton = findViewById(R.id.false_button)

        truebutton.setOnClickListener { view: View ->
            binding.trueButton.setOnClickListener { view: View ->
                checkAnswer(true)
            }
            falsebutton.setOnClickListener { view: View ->
                binding.falseButton.setOnClickListener { view: View ->
                    checkAnswer(false)
                }
                binding.nextButton.setOnClickListener {
                   quizViewModel.moveToNext()
                    updateQuestion()
                }
                binding.prevButton.setOnClickListener {
                    quizViewModel.moveToPrev()
                    updateQuestion()
                    }
                    updateQuestion()
                }
            }
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
        updateQuestion()
    }
    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()
    }
}