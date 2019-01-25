package com.adrian.payment.common

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Utils {

    @Test
    fun md5Example() {
        val entry = "11111111"
        val result = entry.md5()
        assertThat(result, `is`("1bbd886460827015e5d605ed44252251"))
    }
}