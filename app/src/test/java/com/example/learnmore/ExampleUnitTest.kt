package com.example.learnmore

import com.example.learnmore.ui.readCard.WriteCardFragment
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun ApiTest(){
        val writeCardFragment = WriteCardFragment()
        writeCardFragment.gatLearn(1 , 1)
    }
}