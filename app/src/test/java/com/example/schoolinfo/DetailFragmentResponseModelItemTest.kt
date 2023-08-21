package com.example.schoolinfo

import com.example.schoolinfo.model.DetailFragmentResponseModelItem
import org.junit.Assert.assertEquals
import org.junit.Test

class DetailFragmentResponseModelItemTest {
    @Test
    fun testModelItemProperties() {
        val dbn = "123456"
        val numOfSatTestTakers = "500"
        val criticalReadingAvgScore = "550"
        val mathAvgScore = "600"
        val writingAvgScore = "580"
        val schoolName = "Test School"

        val modelItem = DetailFragmentResponseModelItem(
            dbn,
            numOfSatTestTakers,
            criticalReadingAvgScore,
            mathAvgScore,
            writingAvgScore,
            schoolName
        )

        assertEquals(dbn, modelItem.dbn)
        assertEquals(numOfSatTestTakers, modelItem.num_of_sat_test_takers)
        assertEquals(criticalReadingAvgScore, modelItem.sat_critical_reading_avg_score)
        assertEquals(mathAvgScore, modelItem.sat_math_avg_score)
        assertEquals(writingAvgScore, modelItem.sat_writing_avg_score)
        assertEquals(schoolName, modelItem.school_name)
    }

}
