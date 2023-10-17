package com.example.schoolinfo.model

class SchoolApiMapper {
    companion object {
        fun mapToSchoolDetails(item: DetailFragmentResponseModelItem): SchoolDetails {
            return SchoolDetails(
                schoolDbn = item.dbn,
                numberOfSatTestTakers = item.num_of_sat_test_takers.toIntOrNull() ?: 0,
                avgSatCriticalReadingScore = item.sat_critical_reading_avg_score.toIntOrNull() ?: 0,
                avgSatMathScore = item.sat_math_avg_score.toIntOrNull() ?: 0,
                avgSatWritingScore = item.sat_writing_avg_score.toIntOrNull() ?: 0,
                schoolName = item.school_name
            )
        }

    }
}

data class SchoolsList(
    val schoolsList: List<SchoolDetails>
)

data class SchoolDetails(
    val schoolDbn: String,
    val numberOfSatTestTakers: Int?,
    val avgSatCriticalReadingScore: Int?,
    val avgSatMathScore: Int?,
    val avgSatWritingScore: Int?,
    val schoolName: String
)
