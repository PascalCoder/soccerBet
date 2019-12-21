package com.thepascal.soccerstats

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
    fun testIsValidEmail(){
        val testEmailValid = "test1@gmail.com"
        val testEmailInvalid = "test2@"
        assertEquals(true, testEmailValid.isValidEmail())
        assertEquals(false, testEmailInvalid.isValidEmail())
    }

    @Test
    fun testIsValidPassword(){
        val passwordTest = "Xzqo924!"
        assertEquals(true, passwordTest.isValidPassword())
    }
}
