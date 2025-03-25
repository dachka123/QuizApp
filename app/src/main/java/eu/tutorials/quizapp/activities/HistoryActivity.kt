package eu.tutorials.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ItemTouchHelper
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.adapter.QuizResultsAdapter
import eu.tutorials.quizapp.database.DatabaseHandler
import eu.tutorials.quizapp.models.QuizAppModel
import eu.tutorials.quizapp.utils.Constants
import eu.tutorials.quizapp.utils.SwipeDeleteCallback

class HistoryActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuizResultsAdapter
    private lateinit var quizList: ArrayList<QuizAppModel>
    private lateinit var databaseHandler: DatabaseHandler

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar_history)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))

        val btnBackMain : Button = findViewById(R.id.btn_back_main)
        btnBackMain.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.recyclerView_history)
        recyclerView.layoutManager = LinearLayoutManager(this)

        databaseHandler = DatabaseHandler(this)
        quizList = databaseHandler.getQuizResults()

        adapter = QuizResultsAdapter(quizList)
        recyclerView.adapter = adapter
        adapter.setOnclickListener(object : QuizResultsAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val selectedQuiz = quizList[position]

                val intent = Intent(this@HistoryActivity, QuizDetailsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, selectedQuiz.name)
                intent.putExtra(Constants.TOTAL_QUESTIONS, selectedQuiz.totalQuestions)
                intent.putExtra(Constants.CORRECT_ANSWERS, selectedQuiz.correctAnswers)

                startActivity(intent)
            }

        })

        val itemTouchHelper = ItemTouchHelper(SwipeDeleteCallback(this, adapter, quizList, databaseHandler))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        getQuizListFromLocalDB()
        }

    private fun getQuizListFromLocalDB(){
        val rvHistory: RecyclerView = findViewById(R.id.recyclerView_history)
        val tvNoRecordsAvailable: TextView = findViewById(R.id.tvNoRecordsAvailable)
        val dbHandler = DatabaseHandler(this)
        val getQuizList : ArrayList<QuizAppModel> = dbHandler.getQuizResults()

        if(getQuizList.size > 0){
            rvHistory.visibility = View.VISIBLE
            tvNoRecordsAvailable.visibility = View.GONE
        }
        else{
            rvHistory.visibility = View.GONE
            tvNoRecordsAvailable.visibility = View.VISIBLE
        }
    }
}
