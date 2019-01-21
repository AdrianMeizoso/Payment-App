package com.adrian.payment.common

import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class Utils {

    @Test
    fun formatTimeWithHoursMinutesSeconds() {
        val entry = "PT3H45M2S"
        val result = entry.formattedTime
        assertThat(result, `is`("3h 45m 2s"))
    }

    @Test
    fun formatTimeWithMinutesSeconds() {
        val entry = "PT23M56S"
        val result = entry.formattedTime
        assertThat(result, `is`("23m 56s"))
    }

    @Test
    fun formatTimeWithSeconds() {
        val entry = "PT4S"
        val result = entry.formattedTime
        assertThat(result, `is`("4s"))
    }
}