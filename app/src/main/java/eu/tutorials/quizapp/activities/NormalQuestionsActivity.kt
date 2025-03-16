package eu.tutorials.quizapp.activities

import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import eu.tutorials.quizapp.utils.Constants
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.models.Question

class NormalQuestionsActivity : BaseActivity(),View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0
    private var mDifficultyLevel: String? = null

    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var ivImage: ImageView? = null


    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null
    private var btnGoBack: Button? = null

    private var mAnswerRevealed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_normal_questions)

        mUserName = intent.getStringExtra(Constants.USER_NAME)//retrieve name here
        mDifficultyLevel = intent.getStringExtra(Constants.DIFFICULTY_LEVEL)

        progressBar = findViewById(R.id.progressBar_normal)
        tvProgress = findViewById(R.id.tv_progress_normal)
        tvQuestion = findViewById(R.id.tv_question_normal)
        ivImage = findViewById(R.id.iv_image_normal)

        tvOptionOne = findViewById(R.id.tv_option_one_normal)
        tvOptionTwo = findViewById(R.id.tv_option_two_normal)
        tvOptionThree = findViewById(R.id.tv_option_three_normal)
        tvOptionFour = findViewById(R.id.tv_option_four_normal)
        btnSubmit = findViewById(R.id.btn_submit_normal)
        btnGoBack = findViewById(R.id.btn_go_back_normal)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
        btnGoBack?.setOnClickListener(this)

        mQuestionsList = Constants.getNormalQuestions()
        mQuestionsList?.shuffle()
        progressBar?.max = mQuestionsList?.size ?: 10

        setQuestion()
    }

    private fun setQuestion() {
        //to reset color after moving next question
        defaultOptionsView()

        enableOptions()
        //displaying entire screen after name will be written
        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        progressBar?.progress = mCurrentPosition
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"
        tvQuestion?.text = question.question
        ivImage?.setImageResource(question.image)

        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if(mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "Finish"
        }
        else{
            btnSubmit?.text = "Submit"
        }
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv:TextView,selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)

        ObjectAnimator.ofFloat(tv, "scaleX", 1.1f, 1.0f).setDuration(150).start()
        ObjectAnimator.ofFloat(tv, "scaleY", 1.1f, 1.0f).setDuration(150).start()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one_normal -> {
                tvOptionOne?.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.tv_option_two_normal -> {
                tvOptionTwo?.let {
                    selectedOptionView(it,2)
                }
            }
            R.id.tv_option_three_normal -> {
                tvOptionThree?.let {
                    selectedOptionView(it,3)
                }
            }
            R.id.tv_option_four_normal -> {
                tvOptionFour?.let {
                    selectedOptionView(it,4)
                }
            }

            R.id.btn_submit_normal -> {
                if (!mAnswerRevealed) { // If answer is not revealed, handle normal submit logic
                    if (mSelectedOptionPosition == 0) {
                        // Show a toast message if no option is selected
                        Toast.makeText(this, "Please select an answer before submitting!", Toast.LENGTH_SHORT).show()
                    } else {
                        val question = mQuestionsList?.get(mCurrentPosition - 1)

                        if (question!!.correctAnswer != mSelectedOptionPosition) {
                            answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                        } else {
                            mCorrectAnswers++
                        }
                        answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                        // Disable options after submitting
                        disableOptions()

                        if (mCurrentPosition == mQuestionsList!!.size) {
                            btnSubmit?.text = "Finish"
                        } else {
                            btnSubmit?.text = "Go to next question"
                        }

                        // Mark that the answer has been revealed
                        mAnswerRevealed = true
                    }
                } else { // If answer is revealed, move to the next question or finish the quiz
                    mCurrentPosition++
                    if (mCurrentPosition <= mQuestionsList!!.size) {
                        setQuestion()
                    } else {
                        // Move to the ResultActivity after the last question
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, mUserName)
                        intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                        intent.putExtra(Constants.DIFFICULTY_LEVEL, mDifficultyLevel)
                        startActivity(intent)
                        finish()
                    }

                    // Reset for the next question
                    mSelectedOptionPosition = 0
                    mAnswerRevealed = false
                }
            }
            R.id.btn_go_back_normal -> {
                val intent = Intent(this,LevelActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun answerView(answer: Int,drawableView: Int){
        when(answer){
            1 ->{
                tvOptionOne?.background = ContextCompat.getDrawable(this,drawableView)
            }
            2 ->{
                tvOptionTwo?.background = ContextCompat.getDrawable(this,drawableView)
            }
            3 ->{
                tvOptionThree?.background = ContextCompat.getDrawable(this,drawableView)
            }
            4 ->{
                tvOptionFour?.background = ContextCompat.getDrawable(this,drawableView)
            }
        }
    }
    private fun disableOptions(){
        tvOptionOne?.isClickable = false
        tvOptionTwo?.isClickable = false
        tvOptionThree?.isClickable = false
        tvOptionFour?.isClickable = false
    }

    private fun enableOptions(){
        tvOptionOne?.isClickable = true
        tvOptionTwo?.isClickable = true
        tvOptionThree?.isClickable = true
        tvOptionFour?.isClickable = true
    }
}
