package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import kotlinx.serialization.Serializable
import qed.sut.dairymax_REST.Breed
import qed.sut.dairymax_REST.Contour

/**
 * Monthly pasture growth data for a farm's region.
 * Maps to: dmpasturegrowth table
 */
@Serializable
data class PastureGrowth(
    val month: Int,                  // mnth (1-12)
    val monthAbbr: String,           // moyr (Jan, Feb, etc.)
    val pastureGrowth: Double,       // pasturegrowth
    val pastureME: Double,           // pastureME (Metabolizable Energy)
    val pastureCP: Double,           // pastureCP (Crude Protein)
    val pastureLosses: Double        // pastureLosses
)

@Serializable
data class PastureSaveRequest(
    val pastureMonths: List<PastureGrowth>,
    val openingSilageStock: Double? = null,
    val closingSilageStock: Double? = null
)

@Serializable
data class PastureDataResponse(
    val pastureMonths: List<PastureGrowth>,
    val openingSilageStock: Double? = null,
    val closingSilageStock: Double? = null
)

/**
 * Monthly calving pattern split by parity (lactation number).
 * Maps to: dmdataentrycalving table
 *
 * Parity groups:
 * - first: First-lactation cows (heifers)
 * - second: Second-lactation cows
 * - third: Third-lactation cows
 * - older: Fourth+ lactation cows
 * - calving: Total (sum of all parities) - redundant but kept for compatibility
 */
@Serializable
data class CalvingPattern(
    val month: Int,                  // mnth (1-12)
    val monthAbbr: String,           // moyr (Jan, Feb, etc.)
    val calving: Int,                // Total cows calving (sum of parities)
    val first: Int,                  // First-lactation cows
    val second: Int,                 // Second-lactation cows
    val third: Int,                  // Third-lactation cows
    val older: Int                   // Fourth+ lactation cows
)

/**
 * Farm-level parameters for optimization.
 * Single values per farm (not time-series data).
 * Maps to: dmdataentry table
 */
@Serializable
data class FarmParameters(
    // Animal characteristics
    val matureCowWeight: Int? = null,
    val avgDistance: Int? = null,
    val breed: Breed? = null,
    val genotypeAdjustment: Int? = null,

    // Milk payment structure ($/kg component)
    val payoutFat: Double? = null,
    val payoutProtein: Double? = null,
    val payoutCasein: Double? = null,
    val payoutAlbumin: Double? = null,
    val payoutLactose: Double? = null,
    val payoutVolume: Double? = null,

    // Costs
    val fertiliserCost: Double? = null,
    val grassSilageCost: Double? = null,
    val valueReplacement: Double? = null,

    // Losses and waste (percentages)
    val percSilageLosses: Double? = null,
    val silageMakingLosses: Double? = null,
    val percSupplementLosses: Double? = null,

    // Farm physical characteristics
    val contourWalked: Contour? = null,
    val useGrazedCrops: Boolean? = null,

    // Nutrition supplements ($/day or $/cow)
    val macroMinerals: Boolean? = null,
    val microMinerals: Boolean? = null,
    val vitamins: Boolean? = null,
    val dcad: Boolean? = null,

    // Formulation settings
    val formulationMethod: String? = null,  // 'M' = Maximize Profit, 'P' = Production Target

    // Production targets
    val prodTarget: Double? = null,
    val calvingInt: Int? = null,          // Calving interval (days)
    val dryPeriod: Int? = null,           // Dry period (days)
    val milkFat: Double? = null,          // Target milk fat %
    val milkProt: Double? = null,         // Target milk protein %
    val milkLactose: Double? = null,      // Target milk lactose %

    // Herd structure
    val nrProdGroups: Int? = null,
    val nrYoungstockGroups: Int? = null,
    val cownumAgeStruct: Int? = null,
    val percFirst: Double? = null,        // % first-lactation cows
    val percSecond: Double? = null,       // % second-lactation cows
    val percThird: Double? = null,        // % third-lactation cows
    val percOlder: Double? = null,        // % fourth+ lactation cows
    val openingBCS: Double? = null,       // opening body condition score
    val closingBCS: Double? = null,       // closing body condition score
    val minBCS: Double? = null,           // minimum value for body condition score
    val maxBCS: Double? = null,           // maximum value for body condition score
    val minPeNDF: Double? = null,         // minimum level of peNDF (%)
    val maxFatPercent: Double? = null,           // maximum allowable fat level
) {
    companion object {
        fun createDefault() = FarmParameters(
            matureCowWeight = 500,
            avgDistance =  5,
            breed = Breed.FRIESIAN,
            genotypeAdjustment = 0,

            payoutFat =  10.0,
            payoutProtein =  10.0,
            payoutCasein =  0.0,
            payoutAlbumin =  0.0,
            payoutLactose =  0.0,
            payoutVolume =  0.0,

            fertiliserCost =  400.0,
            grassSilageCost =  200.0,
            valueReplacement =  2000.0,

            // =  and waste (percentages)
            percSilageLosses =  15.0,
            silageMakingLosses =  10.0,
            percSupplementLosses =  15.0,

            // =  physical characteristics
            contourWalked =  Contour.FLAT,
            useGrazedCrops =  true,

            // =  supplements ($/day or $/cow)
            macroMinerals =  false,
            microMinerals =  false,
            vitamins =  false,
            dcad =  false,

            // =  settings
            formulationMethod =  "M",  // 'M' = Maximize Profit, 'P' = Production Target

            prodTarget =  300.0,
            calvingInt =  265,          // Calving interval (days)
            dryPeriod =  60,           // Dry period (days)
            milkFat =  4.0,          // Target milk fat %
            milkProt =  3.2,         // Target milk protein %
            milkLactose =  4.2,      // Target milk lactose %

            // =  structure
            nrProdGroups =  2,
            nrYoungstockGroups =  2,
            cownumAgeStruct =  20,
            percFirst =  20.0,        // % first-lactation cows
            percSecond =  30.0,       // % second-lactation cows
            percThird =  30.0,        // % third-lactation cows
            percOlder =  20.0,         // % fourth+ lactation cows
            openingBCS = 4.0,
            closingBCS = 4.0,
            minBCS = 3.0,
            maxBCS = 7.0,
            minPeNDF = 30.0,
            maxFatPercent = 7.0,
        )
    }
}

/**
 * DIM (Days In Milk) range for a production group.
 * Defines the calving period (month range) for this production group.
 *
 * Example scenarios:
 * - Single group (spring calving): dimFrom=1, dimTo=12 (Jan-Dec, full year)
 * - Two groups: Group 1: dimFrom=1, dimTo=6 (Jan-Jun, autumn calving)
 *               Group 2: dimFrom=7, dimTo=12 (Jul-Dec, spring calving)
 *
 * Maps to: dmdataentryprodgroups table
 */
@Serializable
data class MilkingCowGroup(
    val prodGroup: Int,      // Production group number
    val dimFrom: Int,        // Start month of calving period (1-12)
    val dimTo: Int,           // End month of calving period (1-12)
    override val name : String
) : IFarmManagementGroup {
    // Mutable list, populated after animal groups are created
    @Transient
    private val _animalGroups: MutableList<ILPAnimalGroup> = mutableListOf()
    override val animalGroups: List<ILPAnimalGroup> get() = _animalGroups
    override val showMilkMetrics: Boolean get() = true
    override fun addAnimalGroup(group: ILPAnimalGroup) {
        _animalGroups.add(group)
    }
}

/**
 * Group size by parity for a production group.
 * Shows how many cows of each parity are in this group.
 * Maps to: dmdataentryprodgroupparities table
 */
@Serializable
data class GroupSize(
    val prodGroup: String,   // Production group number of milking cow group
    val parity: Int,         // Lactation number (1=first, 2=second, etc.)
    val grpSize: Int         // Number of cows
)

/**
 * Combined production group data.
 * Includes both DIM ranges and group sizes by parity.
 */
@Serializable
data class ProductionGroupData(
    val milkingCowGroups: List<MilkingCowGroup>,
    val groupSizes: List<GroupSize>,
)

/**
 * Youngstock (heifer) group definition.
 * Heifers are grouped by age before they enter production.
 * Maps to: dmdataentryyoungstock table
 */
@Serializable
data class YoungstockGroup(
    val heiferGroup: Int,    // Group number
    val ageFrom: Int,        // Age in months (start of range)
    val ageTo: Int,          // Age in months (end of range)
    val groupSize: Int,       // Number of heifers in group
    override val name: String
) : IFarmManagementGroup {

    @Transient
    private val _animalGroups: MutableList<ILPAnimalGroup> = mutableListOf()

    override val animalGroups: List<ILPAnimalGroup> get() = _animalGroups

    override val showMilkMetrics: Boolean get() = false

    override fun addAnimalGroup(group: ILPAnimalGroup) {
        _animalGroups.add(group)
    }
}

/**
 * Ingredient owner information.
 * Ingredients can be owned by DairyMax (system) or specific users.
 */
@Serializable
data class IngredientOwner(
    val company_id: Int,       // 0 = DairyMax, >0 = user ID
    val name: String         // "DairyMax" or username
)

/**
 * Grazed crop ingredient with farm-specific or default values.
 * Alternative land uses to pasture (kale, turnips, fodder beet, etc.).
 * Values are merged: farm-specific if exists, else system defaults.
 * Maps to: dmdataentrygrazedcrops (farm) or def_dataentrygrazedcrops (defaults)
 */
@Serializable
data class GrazedCrop(
    val ingId: Int,
    val ingCode: String,
    val cost: Double? = null,
    val dryMatter: Double? = null,
    val entryDryMatter: Double? = null,
    val owner: String,
    val isUsed: Boolean? = null,
    val costPerHa: Int? = null,
    val area: Int? = null,
    val yield: Int? = null,
    val sowingMonth: Int? = null,
    val endUse: Int? = null,                // End of land use
    val startFeeding: Int? = null,
    val endFeeding: Int? = null,
    val wastage: Int? = null                // Wastage percentage (not monthsUnused)
)

/**
 * Grazed crops grouped by ingredient owner.
 * System ingredients (DairyMax) and user-specific ingredients separated.
 */
@Serializable
data class GrazedCropsByOwner(
    val owner: IngredientOwner,
    val feedstuff: List<GrazedCrop>
)

/**
 * Aggregated response with all farm data for optimization.
 * This is the main response for GET /api/calculate/{farmId}
 */
@Serializable
data class CalculateDataResponse(
    val farmId: Int,
    val pasture: List<PastureGrowth>,
    val calving: List<CalvingPattern>,
    val parameters: FarmParameters?,
    val productionGroups: ProductionGroupData,
    val youngstock: List<YoungstockGroup>,
    val grazedCrops: List<GrazedCropsByOwner>
)

// Request parameters
data class ConstraintListParams(
    val farmId: Int,
    val groupName: String  // e.g., "Silage", "Hay", "Pasture"
)


@Serializable
data class SupplementsByGroup(
    val groupId: Int,
    val groupDescrip: String,
    val ingredients: List<SupplementIngredient>
)

@Serializable
data class SupplementIngredient(
    val ingId: Int,
    val ingCode: String,
    val ingDescrip: String,
    val isUsed: Boolean,
    val pricePerTonne: Int,
    val availFrom: Int,
    val availTo: Int,
)


@Serializable
data class FeedbunkersByGroup(
    val groupId: Int,
    val groupDescrip: String,
    val items: List<FeedbunkerItem>
)

@Serializable
data class FeedbunkerItem(
    val ingId: Int,
    val ingCode: String,
    val ingDescrip: String,
    val canPurchase: Boolean,
    val pricePerTonne: Int,
    val inStock: Int,
    val startMonth: Int,
    val endMonth: Int,
    val replacement: Boolean,
    val ownCrop: Boolean,
    val numOfHa: Int,
    val startGrowing: Int,
    val endGrowing: Int,
    val yieldPerHa: Int,
    val costPerHa: Int,
    val purchasePrice: Int,
//    val cost: Int
)