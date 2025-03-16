package eu.tutorials.quizapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import eu.tutorials.quizapp.utils.Constants
import eu.tutorials.quizapp.R

class LevelActivity : BaseActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_level)

        val userName = intent.getStringExtra(Constants.USER_NAME)

        val btn_easy: Button = findViewById(R.id.btn_easy)
        btn_easy.setOnClickListener{
            val intent = Intent(this,EasyQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            intent.putExtra(Constants.DIFFICULTY_LEVEL, "easy")
            startActivity(intent)
            finish()
        }

        val btn_normal: Button = findViewById(R.id.btn_normal)
        btn_normal.setOnClickListener{
            val intent = Intent(this,NormalQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            intent.putExtra(Constants.DIFFICULTY_LEVEL, "normal")
            startActivity(intent)
            finish()
        }

        val btn_hard: Button = findViewById(R.id.btn_hard)
        btn_hard.setOnClickListener{
            val intent = Intent(this,HardQuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            intent.putExtra(Constants.DIFFICULTY_LEVEL, "hard")
            startActivity(intent)
            finish()
        }

        val btn_super_hard: Button = findViewById(R.id.btn_super_hard)
        btn_super_hard.setOnClickListener{
            val intent = Intent(this,SuperHard_QuestionsActivity::class.java)
            intent.putExtra(Constants.USER_NAME, userName)
            intent.putExtra(Constants.DIFFICULTY_LEVEL, "super hard")
            startActivity(intent)
            finish()
        }
    }
}