package eu.tutorials.quizapp.activities

import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import eu.tutorials.quizapp.R
import eu.tutorials.quizapp.utils.Constants.isNetworkAvailable

open class BaseActivity : AppCompatActivity() {

    private var mProgressDialog: Dialog? = null
    private lateinit var networkReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_base)

        showProgressDialog()

        // Check network and handle
        if (!isNetworkAvailable(this)) {
            hideProgressDialog()
            showNoInternetDialog()
        } else {
            hideProgressDialog()
        }

        networkReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (!isNetworkAvailable(context!!)) {
                    showNoInternetDialog()
                }
            }
        }

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkReceiver)
    }

    private fun showProgressDialog() {
        mProgressDialog = Dialog(this)
        mProgressDialog!!.setContentView(R.layout.dialog_custom_progress)
        mProgressDialog!!.setCancelable(false)
        mProgressDialog!!.show()
    }
    private fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }

    private fun showNoInternetDialog() {
        val dialog = AlertDialog.Builder(this).setTitle("No Internet Connection").setMessage("You're offline. Please check your internet connection.").setCancelable(false)
            .setPositiveButton("Retry") { dialog, _ ->
                showProgressDialog()
                if (isNetworkAvailable(this)) {
                    hideProgressDialog()
                } else {
                    hideProgressDialog()
                    showNoInternetDialog() // Keep retrying
                }
                dialog.dismiss()
            }
            .setNegativeButton("Open Settings") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(android.provider.Settings.ACTION_WIFI_SETTINGS)
                startActivity(intent)
            }
            .create()

        dialog.show()
    }
}