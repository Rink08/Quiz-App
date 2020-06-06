package com.example.quizapp

//intent data class is created to initialize intent constant
data class IntentConstant(
             val REQUEST_CODE: Int = 1,
             val SHARED_PREFS: String = "sharedPrefs",
             val KEY_HIGH_SCORE: String = "KeyHighScore",
             val HIGH_SCORE: String = "highscore"
)