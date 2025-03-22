package eu.tutorials.quizapp.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import eu.tutorials.quizapp.utils.Constants
import eu.tutorials.quizapp.R

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnStart : Button = findViewById(R.id.btn_start)
        val btnSeeScores : Button = findViewById(R.id.btn_See_Scores)
        val etName : EditText = findViewById(R.id.et_name)

        btnStart.setOnClickListener{
            if(etName.text.isEmpty()){
                Toast.makeText(this, "please enter name",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, LevelActivity::class.java)
                intent.putExtra(Constants.USER_NAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }

        btnSeeScores.setOnClickListener{
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}