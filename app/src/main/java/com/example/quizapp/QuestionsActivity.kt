package com.example.quizapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room

class QuestionsActivity : AppCompatActivity() {

    //object of data class IntentConstant
    private val obj = IntentConstant()

    //list of type String to store multiple questions
    private val questions = listOf(
        "Who is the creator of Kotlin?",
        "Java language can be used for?",
        "Number of bit used by IPv6 addressing",
        "Where is the headquter of Microsoft office located?",
        "Computer Hard Disk was first introduced in 1956 by?",
        "Which computer program converts assembly language to machine language?",
        "Which one is an example of connectionless protocols?",
        "What is the full form of PDF")

    //list of type String to store options
    private val op1 = listOf("Google","JetBrains","Oracle","Microsoft")
    private val op2 = listOf("Android","Webapp","Game","All")
    private val op3 = listOf("32bit","64bit","128bit","256bit")
    private val op4 = listOf("Texas","New York","California","Washington")
    private val op5 = listOf("Dell","Apple","Microsoft","IBM")
    private val op6 = listOf("Interpreter","Compiler","Assembler","Comparator")
    private val op7 = listOf("TCP","UDP","Frame Relay","ARP")
    private val op8 = listOf("Printed Document Format","Public Document Format","Portable Document Format","Published Document Format")

    //nested list of options
    private val optionList = listOf(op1,op2,op3,op4,op5,op6,op7,op8)

    //list of type String to store correct answers
    private val ans = listOf("JetBrains","All","128bit","Washington","IBM","Assembler","UDP","Portable Document Format")

    //late initialization for components and are of non-nullable type
    lateinit var textViewQuestion: TextView
    lateinit var textViewScore: TextView
    lateinit var textViewQuestionCount: TextView
    lateinit var rbGroup: RadioGroup
    lateinit var rb1: RadioButton
    lateinit var rb2: RadioButton
    lateinit var rb3: RadioButton
    lateinit var rb4: RadioButton
    lateinit var confirmBtn: Button
    lateinit var textColorDefaultRb: ColorStateList

    private var totalQuestionCount:Int = 0
    private var questionCounter:Int = 0
    private var score: Int = 0
    private var i: Int = 0
    private  var  answered:Boolean = false

    //shuffled method is used to randomly shuffle the questions
    private val questionShuffle = (questions.indices).shuffled()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        //lateinit variable assigned values
        textViewQuestion = findViewById(R.id.textviewquestion)
        textViewScore = findViewById(R.id.textviewscore)
        textViewQuestionCount = findViewById(R.id.textviewqcount)
        rbGroup = findViewById(R.id.radiogroup)
        rb1 = findViewById(R.id.rd1)
        rb2 = findViewById(R.id.rd2)
        rb3 = findViewById(R.id.rd3)
        rb4 = findViewById(R.id.rd4)
        confirmBtn = findViewById(R.id.btnsubmitnext)


        textColorDefaultRb = rb1.textColors
        totalQuestionCount = questions.size   //total number of questions

        showNextQuestion()     //method to display questions

        confirmBtn.setOnClickListener {
            if(!answered){
                 if(rb1.isChecked || rb2.isChecked || rb3.isChecked || rb4.isChecked){
                     checkAnswer()     //method to check answer if anyone option is selected
                 }else{
                      Toast.makeText(this,"Please select the option",Toast.LENGTH_LONG).show()   //display message if no option is selected
                 }
            }else{
                //set default color to all options
                rb1.setTextColor(this.textColorDefaultRb)
                rb2.setTextColor(this.textColorDefaultRb)
                rb3.setTextColor(this.textColorDefaultRb)
                rb4.setTextColor(this.textColorDefaultRb)
                showNextQuestion()  //method to display next questions
            }
        }

    }

     @SuppressLint("SetTextI18n")
     private fun showNextQuestion(){

         //all options are initially clickable
         rb1.isClickable = true
         rb2.isClickable = true
         rb3.isClickable = true
         rb4.isClickable = true

        rbGroup.clearCheck()  //initially non option is selected

         //to check number of questions displayed are less then total number of questions
        if(questionCounter < totalQuestionCount){

            //randomly shuffled questions and options are displayed on screen
             i = questionShuffle[questionCounter]
            textViewQuestion.text = questions[i]
            rb1.text = optionList[i][0]
            rb2.text = optionList[i][1]
            rb3.text = optionList[i][2]
            rb4.text = optionList[i][3]
            questionCounter++
            textViewQuestionCount.text = "Question: $questionCounter/ $totalQuestionCount"  //increment and display question count
            answered = false
            confirmBtn.text = "Confirm"
        }
        else {
            //intent is used to pass the hight score to the home/main screen
            intent = Intent()
            intent.putExtra(obj.HIGH_SCORE,score)  //sends the data to MainActivity
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }
    private fun finishQuiz(){

    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer() {
        //once the answer is confirm the user cannot change it
        rb1.isClickable = false
        rb2.isClickable = false
        rb3.isClickable = false
        rb4.isClickable = false
        answered = true
        val rbSelected: RadioButton = findViewById(rbGroup.checkedRadioButtonId)
        val answer = rbSelected.text.toString()  //value of selected option is stored to check whether it right or wrong

        if (answer == ans[i]) {
            //if selected option is correct the score increment and that option turns Green to indicate selected option is right answer
            score++
            textViewScore.text = "Score: $score"
             rbSelected.setTextColor(Color.GREEN)
            Toast.makeText(this,"Correct answer",Toast.LENGTH_LONG).show()

        }else{
            //if selected option is wrong then it turns red
            rbSelected.setTextColor(Color.RED)
            Toast.makeText(this,"Wrong answer",Toast.LENGTH_LONG).show()

            // by using when right option is turn Green
            when(ans[i]){
                optionList[i][0] ->  rb1.setTextColor(Color.GREEN)
                optionList[i][1] ->  rb2.setTextColor(Color.GREEN)
                optionList[i][2] ->  rb3.setTextColor(Color.GREEN)
                optionList[i][3] ->  rb4.setTextColor(Color.GREEN)
            }
        }

        if(questionCounter <= totalQuestionCount) {
            confirmBtn.text = "Next"
        } else{
            confirmBtn.text = "Finish"
        }

    }

}
