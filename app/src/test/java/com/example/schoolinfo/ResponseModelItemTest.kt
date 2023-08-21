package com.example.schoolinfo

import com.example.schoolinfo.model.ResponseModelItem
import org.junit.Assert.assertEquals
import org.junit.Test

class ResponseModelItemTest {

    @Test
    fun testModelProperties() {
        // Create an instance of ResponseModelItem
        val model = ResponseModelItem(
            school_name = "Example School",
            dbn = "123"
        )

        // Verify that the properties have the expected values
        assertEquals("Example School", model.school_name)
        assertEquals("123", model.dbn)
    }

}
