package com.example.quizapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException

///**
// * Instrumented test, which will execute on an Android device.
// *
// * See [testing documentation](http://d.android.com/tools/testing).
// */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var todoDao: QuestionDao
    private lateinit var db: QuestionDatabase
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.quizapp", appContext.packageName)
    }
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, QuestionDatabase::class.java).build()
        //todoDao = db.get()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val todo: Questions = Questions(1, "Python version","1","2","3","1.5",3)
        todoDao.insertQ(todo)

    }

}
