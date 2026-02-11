package qed.testbaseclass.sut.dairymax_REST.delphi_lp_model

import kotlinx.serialization.Serializable

// =============================================================================
// Data Classes for Economics Report
// =============================================================================

/**
 * Complete economics report for the farm.
 */
@Serializable
data class EconomicsReport(
    val monthly: List<MonthlyEconomics>,
    val annual: AnnualEconomics,
    val sensitivity: PriceSensitivity,
    val limitingFactors: List<LimitingFactor>,
    val firstLpMonth: Int = 0
)

/**
 * Economics for a single month (LP period).
 */
@Serializable
data class MonthlyEconomics(
    val period: Int,
    val monthName: String,
    val calendarMonth: Int,           // 1-12

    // Farm totals for the month (per day)
    val totalMilkProductionL: Double,
    val totalMilkSolidsKg: Double,
    val totalMilkRevenue: Double,
    val totalFeedCost: Double,
    val totalMargin: Double,

    // Per-group breakdown
    val groups: List<GroupEconomics>
)

/**
 * Economics for a production group in a specific month.
 */
@Serializable
data class GroupEconomics(
    val groupLabel: String,
    val numAnimals: Int,
    val lactationMonth: Int,          // 0 for youngstock/dry

    // Per cow per day
    val milkProductionL: Double,
    val milkSolidsKg: Double,
    val milkRevenue: Double,
    val feedCost: Double,
    val marginOverFeed: Double,

    // Cost breakdown per cow per day
    val pastureCost: Double,
    val silageCost: Double,
    val supplementCost: Double,

    // Group totals per day
    val groupMilkRevenue: Double,
    val groupFeedCost: Double,
    val groupMargin: Double,

    // Efficiency metrics
    val feedCostPerKgMilk: Double?,   // $/kg milk produced
    val feedCostPerKgMS: Double?,     // $/kg MS produced
    val breakevenMilkPrice: Double?,  // Milk price at which margin = 0 ($/L)
    val breakevenMilkPriceMS: Double? // MS price at which margin = 0 ($/kg MS)
)

/**
 * Annual economics summary.
 */
@Serializable
data class AnnualEconomics(
    // Totals for the year
    val totalMilkProductionL: Double,
    val totalMilkSolidsKg: Double,
    val totalMilkRevenue: Double,
    val totalFeedCost: Double,
    val totalMargin: Double,

    // Per hectare per year
    val milkRevenuePerHa: Double,
    val feedCostPerHa: Double,
    val marginPerHa: Double,
    val milkSolidsPerHa: Double,

    // Per cow per year
    val milkRevenuePerCow: Double,
    val feedCostPerCow: Double,
    val marginPerCow: Double,
    val milkSolidsPerCow: Double,

    // Efficiency metrics
    val feedCostPerKgMilk: Double,
    val feedCostPerKgMS: Double,
    val marginPerKgMS: Double,

    // Breakeven prices
    val breakevenMilkPricePerL: Double,   // Price per L at which margin = 0
    val breakevenMilkPricePerKgMS: Double // Price per kg MS at which margin = 0
)

/**
 * Price sensitivity analysis.
 */
@Serializable
data class PriceSensitivity(
    // Current prices
    val currentMilkPricePerL: Double,
    val currentMilkPricePerKgMS: Double,

    // Breakeven (farm-level)
    val breakevenMilkPricePerL: Double,
    val breakevenMilkPricePerKgMS: Double,

    // Sensitivity metrics
    val marginPerCentMilkPriceChange: Double,  // $ change in annual margin per 1c/L milk price change
    val safetyMarginPercent: Double,           // % milk price can drop before making a loss
    val safetyMarginCentsPerL: Double,         // How many cents/L above breakeven

    // Per-period sensitivity
    val periodSensitivity: List<PeriodPriceSensitivity>,

    // Reformulation triggers
    val reformulationTriggers: ReformulationTriggers
)

/**
 * Price sensitivity for a specific period.
 */
@Serializable
data class PeriodPriceSensitivity(
    val period: Int,
    val monthName: String,
    val breakevenMilkPricePerL: Double?,
    val breakevenMilkPricePerKgMS: Double?,
    val marginAtCurrentPrice: Double,
    val isHighRisk: Boolean  // true if breakeven is close to current price
)

/**
 * Triggers that suggest when to reformulate.
 */
@Serializable
data class ReformulationTriggers(
    // Milk price thresholds
    val reformulateIfMilkPriceBelow: Double?,   // $/L - consider reformulating
    val urgentReformulateIfBelow: Double?,      // $/L - definitely reformulate

    // Supplement price thresholds
    val keySupplementSensitivities: List<SupplementSensitivity>,

    // Recommendations
    val recommendations: List<String>
)

/**
 * Price sensitivity for a key supplement.
 */
@Serializable
data class SupplementSensitivity(
    val ingredientCode: String,
    val ingredientName: String,
    val currentPricePerTonne: Double,
    val breakevenPricePerTonne: Double?,  // Price at which it would enter/exit solution
    val isIncluded: Boolean,
    val recommendation: String?
)

/**
 * Limiting factor from binding constraints.
 */
@Serializable
data class LimitingFactor(
    val constraintName: String,
    val category: String,              // e.g., "Feed Supply", "Intake Capacity", "Nutrient"
    val constraintType: String,        // description of type of constraint
    val groupId: Int?,                 // Group ID if constraint applies to a specific group
    val groupName: String?,            // Human-readable group name
    val period: Int?,
    val monthName: String?,
    val shadowPrice: Double,           // Dual value ($/unit)
    val interpretation: String,        // Human-readable explanation
    val recommendedAction: String?,    // What the farmer could do about it
    val actualValue: Double?,          // The value achieved in the solution
    val constraintLimit: Double?,      // The RHS constraint value
    val unit: String?,                 // Unit of measurement (e.g., "kg DM", "BCS units")
    val bindingType: String?,          // "min", "max", or "eq"
    val slack: Double?                 // Difference between actual and limit
)
