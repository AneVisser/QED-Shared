package qed.testbaseclass.sut.dairymax_REST.delphi_lp_model

import kotlinx.serialization.Serializable

// =============================================================================
// Data Classes
// =============================================================================

/**
 * Complete nutrient report for the solution.
 */
@Serializable
data class NutrientReport(
    val monthly: List<MonthlyNutrientData>
)

/**
 * Nutrient data for a single month.
 */
@Serializable
data class MonthlyNutrientData(
    val period: Int,
    val monthName: String,
    val groups: List<GroupNutrientData>
)

/**
 * Nutrient data for a production group in a month.
 */
@Serializable
data class GroupNutrientData(
    val groupLabel: String,
    val numAnimals: Int,
    val lactationMonth: Int,            // 1-12, or 0 for non-milking
    val nutrients: List<NutrientReportRow>
)

/**
 * Single nutrient row matching legacy format.
 * Columns: min | max | result | unit | % of min | % of max
 */
@Serializable
data class NutrientReportRow(
    val code: String,                   // e.g., "CP", "ME", "RFC"
    val name: String,                   // e.g., "Crude Protein"
    val min: Double?,                   // Minimum constraint (null = unconstrained)
    val max: Double?,                   // Maximum constraint (null = unconstrained)
    val result: Double,                 // Actual value achieved
    val unit: String,                   // e.g., "% DM", "MJ", "g"
    val percentOfMin: Int?,             // result / min * 100 (null if no min)
    val percentOfMax: Int?,             // result / max * 100 (null if no max)
    val isBinding: Boolean,             // At a constraint limit
    val shadowPrice: Double?            // Value of relaxing constraint
)
