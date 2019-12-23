package com.thepascal.soccerstats

import org.junit.Assert
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun testIsValidEmail(){
        val testEmailValid = "test1@gmail.com"
        val testEmailInvalid = "test2@"
        Assert.assertEquals(true, isEmailValid(testEmailValid))
        Assert.assertEquals(false, isEmailValid(testEmailInvalid))
    }

    @Test
    fun testIsValidPassword(){
        val passwordTest = "Xzqo924!"
        Assert.assertEquals(true, isPasswordValid(passwordTest))
    }
}