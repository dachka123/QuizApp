package eu.tutorials.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import eu.tutorials.quizapp.utils.Constants
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.database.DatabaseHandler
import eu.tutorials.quizapp.models.QuizAppModel

class ResultActivity : BaseActivity() {
    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0
    private var mTotalQuestions: Int = 0
    private var mDifficultyLevel: String? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDifficulty: TextView = findViewById(R.id.tv_difficulty)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val btnFinish: Button = findViewById(R.id.btn_finish)
        val btn_see_scores: Button = findViewById(R.id.btn_see_scores)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        tvName.text = mUserName ?: "Unknown Player"

        mDifficultyLevel = intent.getStringExtra(Constants.DIFFICULTY_LEVEL)
        tvDifficulty.text = "Difficulty Level: $mDifficultyLevel"

        mTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        mCorrectAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvScore.text = "Your score is $mCorrectAnswers out of $mTotalQuestions"


        btnFinish.setOnClickListener {
            val databaseHandler = DatabaseHandler(this)

            val quizResult = QuizAppModel(
                id = 0,
                name = mUserName ?: "Unknown",
                difficulty = mDifficultyLevel ?: "Unknown",
                result = mCorrectAnswers
            )

            // Save result to database
            val insertResult = databaseHandler.addQuizApp(quizResult)

            if (insertResult > 0) {
                // After saving, go to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        btn_see_scores.setOnClickListener{
            val databaseHandler = DatabaseHandler(this)

            val quizResult = QuizAppModel(
                id = 0,
                name = mUserName ?: "Unknown",
                difficulty = mDifficultyLevel ?: "Unknown",
                result = mCorrectAnswers
            )

            val insertResult = databaseHandler.addQuizApp(quizResult)

            if (insertResult > 0) {
                val intent = Intent(this@ResultActivity, HistoryActivity::class.java)
                startActivity(intent)
            }
        }
    }
}