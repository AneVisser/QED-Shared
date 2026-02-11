package qed.testbaseclass.sut.dairymax_REST.lp_model

import kotlinx.serialization.Serializable

@Serializable
data class SummaryChartData(
//    @Deprecated("Probably unused, double-check")
    val id: String,
//    @Deprecated("Probably unused, double-check")
    val function: String,
    val all: List<MonthChartData>,
//    @Deprecated("Probably unused, double-check")
    val ElapsedTimeFormulation: String,
//    @Deprecated("Probably unused, double-check")
    val ElapsedTimeReporting: String,
//    @Deprecated("Probably unused, double-check")
    val ElapsedTimeTotal: String,
//    @Deprecated("Probably unused, double-check")
    val formulationresult: Int
)

// values with a 'W' frefix are for whole farm in tonnes
@Serializable
data class MonthChartData(
    val Month: String,
    val WConservation: Double,
    val WPasture: Double,
    val WSilage: Double,
    val WSupplement: Double,
    val WFeedbunker: Double,
    val WGrazedCrop: Double,
    val WStoredCrop: Double
)
