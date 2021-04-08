package com.hannapapova.counter

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private val id = "counter-25fff"
    private val name = "Counter"

    private val SHARED_PREFS = "sharedPrefs"
    private val COUNTER = "counter"
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupFirebase()

        loadData()
        updateView()

        btn_increase.setOnClickListener {
            counter++
            updateView()
            saveData()
        }

        btn_decrease.setOnClickListener {
            counter--
            updateView()
            saveData()
        }
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(COUNTER, tv_counter.text.toString().toInt())
        editor.apply()
    }

    private fun loadData(){
        val sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        counter = sharedPreferences.getInt(COUNTER, 0)
    }

    private fun updateView(){
        tv_counter.text = counter.toString()
    }

    private fun setupFirebase(){
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name)
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        mFirebaseAnalytics!!.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}