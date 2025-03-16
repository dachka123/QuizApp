package eu.tutorials.quizapp.activities

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import eu.tutorials.quizapp.utils.Constants
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.utils.Constants.isNetworkAvailable

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
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