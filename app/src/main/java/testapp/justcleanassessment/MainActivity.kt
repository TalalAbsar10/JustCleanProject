package testapp.justcleanassessment

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import testapp.justcleanassessment.util.ConnectionReceiver


@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main),ConnectionReceiver.ConnectionReceiverListener {

    var view: FragmentContainerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = findViewById(R.id.main_fragment_container) as FragmentContainerView
        baseContext.registerReceiver(ConnectionReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        MyApplication.instance.setConnectionListener(this)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            val snack = Snackbar.make(view!!,"Connected",Snackbar.LENGTH_SHORT)
            snack.show()
        }
        else {
            val snack = Snackbar.make(view!!,"Network dicconnected",Snackbar.LENGTH_SHORT)
            snack.show()
        }
    }

}