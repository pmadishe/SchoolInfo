package com.example.schoolinfo

import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailFragmentResponseModelItemTest {

    @Test
    fun testModelProperties() {
        // Create an instance of ResponseModelItem
        val model = DetailFragmentResponseModelItem(
            school_name = "Example School",
            dbn = "123",
            num_of_sat_test_takers = "50",
            sat_math_avg_score = "90",
            sat_writing_avg_score = "80",
            sat_critical_reading_avg_score = "75"
        )

        // Verify that the properties have the expected values
        assertEquals("Example School", model.school_name)
        assertEquals("123", model.dbn)
        assertEquals("50", model.num_of_sat_test_takers)
        assertEquals("90", model.sat_math_avg_score)
        assertEquals("80", model.sat_writing_avg_score)
        assertEquals("75", model.sat_critical_reading_avg_score)

    }

}
