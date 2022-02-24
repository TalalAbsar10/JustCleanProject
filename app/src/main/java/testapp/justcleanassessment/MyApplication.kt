package testapp.justcleanassessment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import testapp.justcleanassessment.util.ConnectionReceiver


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance=this
    }

    fun setConnectionListener(listener:ConnectionReceiver.ConnectionReceiverListener){
        ConnectionReceiver.connectionReceiverListener =listener
    }

    companion object{
        @get:Synchronized
        lateinit var instance:MyApplication
    }

}