package eu.tutorials.quizapp.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.database.DatabaseHandler
import eu.tutorials.quizapp.utils.Constants

class QuizDetailsActivity : BaseActivity() {

    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0
    private var mTotalQuestions: Int = 0

    private lateinit var dbHandler: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_details)

        val tvDetailsName: TextView = findViewById(R.id.tvDetailsName)
        val tvDetailsScore: TextView = findViewById(R.id.tvDetailsScore)
        val tvDynamic: TextView = findViewById(R.id.tvDynamic)

        dbHandler = DatabaseHandler(this)

        mUserName = intent.getStringExtra(Constants.USER_NAME)
        tvDetailsName.text = mUserName ?: "Unknown Player"

        mTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        mCorrectAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvDetailsScore.text = "$mCorrectAnswers/$mTotalQuestions"

        if(mCorrectAnswers == 0){
            tvDynamic.text = "Worst result. Try again. You can do your best"
            tvDynamic.setBackgroundColor(Color.RED)
        }
        else if(mCorrectAnswers >= 1 && mCorrectAnswers <= 5){
            tvDynamic.text = "Normal result. Recommending to try again. you can do your best"
            tvDynamic.setBackgroundColor(Color.YELLOW)
        }
        else if(mCorrectAnswers >= 6 && mCorrectAnswers <= 9){
            tvDynamic.text = "Good result. Reccomending to try again. you almost did your best"
            tvDynamic.setBackgroundColor(Color.parseColor("#FFA500"))
        }
        else{
            tvDynamic.text = "Perfect. You are the best into guessing country's flags"
            tvDynamic.setBackgroundColor(Color.GREEN)
        }
    }

}