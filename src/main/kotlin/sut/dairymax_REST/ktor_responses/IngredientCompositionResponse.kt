package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import kotlinx.serialization.Serializable

@Serializable
data class NutrientCompositionResponse(
    val nutId: Int,
    val code: String,              // nutCode
    val description: String,       // nutDescrip
    val value: Double?,            // Effective value (overwrite ?? std) - backward compatible
    val stdValue: Double? = null,  // Parent/master value (for edit UI)
    val overwriteValue: Double? = null, // User's company override (for edit UI)
    val unit: String?,             // Derived from nutUnitNum
    val editUnit: String? = null,
    val displayOrder: Int,          // ord - for UI sorting
    val isCalculated: Boolean = false  // true for for example peNDF, DCAD - makes field read-only
)

@Serializable
data class IngredientCompositionResponse(
    val ingId: Int,
    val nutrients: List<NutrientCompositionResponse>
)

/**
 * API response model for ingredient description/metadata.
 * This is the clean API contract exposed to consumers.
 */
@Serializable
data class IngredientDescriptionResponse(
    val ingId: Int,
    val code: String,              // Ingredient code
    val description: String,       // Ingredient name/description
    val cost: Double?,             // Cost per unit (nullable - may not have cost)
    val dryMatter: Double?,        // Dry matter percentage
    val entryDryMatter: Double?,   // Entry Dry Matter
    val defaultMin: Double?,       // Default minimum inclusion rate
    val defaultMax: Double?,       // Default maximum inclusion rate
    val defaultUnit: String        // Unit for min/max (e.g., "kg/day", "%")
)