package com.mvvm.cryptocapital.utils

import com.mvvm.cryptocapital.utils.Utils.Companion.monetaryDescription
import org.junit.Assert.*
import org.junit.Test

class UtilsTest{

    @Test
    fun testMoneyDescriptionFromDouble(){
        assertEquals("$12.00",12.toDouble().monetaryDescription)
        assertEquals("$1.23 K",1234.toDouble().monetaryDescription)
        assertEquals("$123.46 K",123456.toDouble().monetaryDescription)
        assertEquals("$12.35 M",12345678.toDouble().monetaryDescription)
        assertEquals("$1.23 Bn",1234567890.toDouble().monetaryDescription)
        assertEquals("$123.46 Bn",123456789012.toDouble().monetaryDescription)
        assertEquals("$12.35 Tr",12345678901234.toDouble().monetaryDescription)
    }
}