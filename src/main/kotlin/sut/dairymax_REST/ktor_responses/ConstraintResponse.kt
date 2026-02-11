package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import com.squareup.moshi.JsonClass
import kotlinx.serialization.Serializable

/**
 * Response models for constraint list endpoints
 * These mirror the shared models in the main application
 */

// ============================================================================
// Feedbunker Constraint Responses
// ============================================================================

typealias FeedbunkerConstraintList = List<FeedbunkerConstraint>

@Serializable
@JsonClass(generateAdapter = true)
data class FeedbunkerConstraint(
    val ingId: Int,
    val ingCode: String,
    val ingDescrip: String,
    val cost: Double,

    val canPurchase: Boolean,
    val pricePerTonne: Double,
    val inStock: Double,
    val startMonth: Int,
    val endMonth: Int,
    val startMonthStr: String,
    val endMonthStr: String,

    val replacement: Boolean,
    val ownCrop: Boolean,
    val numOfHa: Int,
    val startGrowing: Int,
    val endGrowing: Int,
    val startGrowingStr: String,
    val endGrowingStr: String,
    val yieldPerHa: Double,
    val costPerHa: Double,
    val purchasePrice: Double,

    val groupId: Int,
    val groupDescrip: String
)

// ============================================================================
// Grazed Crops Constraint Responses
// ============================================================================

typealias GrazedCropsConstraintList = List<GrazedCropConstraint>

@Serializable
data class GrazedCropConstraint(
    val ingId: Int,
    val ingCode: String,
    val ingDescrip: String,
    val cost: Double,

    val used: Boolean,
    val costPerHa: Double,
    val numOfHa: Double,
    val yield: Double,

    val startUse: Int,
    val endUse: Int,
    val startUseStr: String,
    val endUseStr: String,

    val startIntake: Int,
    val endIntake: Int,
    val startIntakeStr: String,
    val endIntakeStr: String,

    val wastage: Double
)

// ============================================================================
// Ingredient Constraints
// ============================================================================

typealias IngredientConstraintList = List<IngredientWithConstraints>

@Serializable
data class IngredientWithConstraints(
    // Base ingredient info
    val ingId: Int,
    val ingCode: String,
    val ingDescrip: String,
    val groupId: Int,
    val groupDescrip: String,

    // List of constraints for this ingredient (one per production group)
    val ingConstList: List<IngredientConstraint>
)

@Serializable
data class IngredientConstraint(
    val prodGroup: Int,         // Production group number (1-N for milking, 100 for dry cows, 101+ for youngstock)
    val minimum: Double?,       // Minimum constraint value (null if not set)
    val maximum: Double?,       // Maximum constraint value (null if not set)
    val constUnit: Int,         // Constraint unit (1=kg DM, 2=kg FM, 3=% of diet, etc.)
    val constUnitStr: String    // Human-readable unit string
)






