package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.*

class MainActivity : AppCompatActivity() {

    private var hs: Int = 0
    private val obj = IntentConstant()

    //late initialization for components and are of non-nullable type
    lateinit var highScoreTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //late init variable assigned values
        val btnstartquiz: Button =  findViewById(R.id.startbtn)
        highScoreTv = findViewById(R.id.score)

        loadHighScore()

        btnstartquiz.setOnClickListener {
            //using intent next activity is started
           intent = Intent(this,QuestionsActivity::class.java)
            startActivityForResult(intent,obj.REQUEST_CODE) //get result
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == obj.REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK) {
                val score: Int = data!!.getIntExtra(obj.HIGH_SCORE, 0)   //gets the data from QuestionActivity
                if(score > hs) updateHighScore(score)  //if current score is greater than higher then its updated
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadHighScore(){
        //SharedPreferences store highest score and retrieve it when application is opened again
        val prefs: SharedPreferences = getSharedPreferences(obj.SHARED_PREFS, Context.MODE_PRIVATE)
        hs = prefs.getInt(obj.KEY_HIGH_SCORE,0)
        highScoreTv.text = "Highscore : $hs"
    }

    @SuppressLint("SetTextI18n")
    private fun updateHighScore(score:Int){
        hs = score
        highScoreTv.text = "Highscore : $hs"
        val prefs: SharedPreferences = getSharedPreferences(obj.SHARED_PREFS, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putInt(obj.KEY_HIGH_SCORE,hs)
        editor.apply()
    }

}

