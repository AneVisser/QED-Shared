import kotlinx.serialization.Serializable

// =============================================================================
// New Data Class for Production Summary
// =============================================================================

/**
 * Production summary with all unit breakdowns.
 * Add this to your data classes file.
 */
@Serializable
data class ProductionSummary(
    // Milk production (litres)
    val milkLitresPerCowPerDay: Double,
    val milkLitresPerCowPerLactationDay: Double,  // NEW
    val milkLitresPerCowPerYear: Double,
    val milkLitresPerHaPerYear: Double,
    val milkLitresTotalYear: Double,

    // Milk solids (kg MS)
    val milkSolidsPerCowPerDay: Double,
    val milkSolidsPerCowPerLactationDay: Double,  // NEW
    val milkSolidsPerCowPerYear: Double,
    val milkSolidsPerHaPerYear: Double,
    val milkSolidsTotalYear: Double,

    // Lactation info
    val lactationLengthMonths: Int,  // NEW - for reference
    val lactationLengthDays: Double  // NEW - for reference
)



@Serializable
data class SummaryResponse(
    val id: String,
    val function: String,
    val nummilkingcows: Int,
    val numyoungstock: Int,
    val area: Int,
    val stockingdensity: Double?,
    val pastureutilisation: Double?,
    val silageutilisation: Double?,
    val supplementutilisation: Double?,
    val breed: String,
    val milkfatperc: Double,
    val milkprotperc: Double,
    val milkprice: Double,
    val milkpriceMS: String,
    val maturecowweight: Int,

    val incomefrommilk: List<String?>,
    val feedcost: List<String?>,
    val pasturecost: List<String?>,
    val silagecost: List<String?>,
    val supplementcost: List<String?>,
    val marginoverfeed: List<String?>,
    val feedcostreplacement: List<String?>,
    val marginoverfeedfromobjective: List<String?>,
    val pasturedmgrown: List<String?>,
    val pasturedmintake: List<String?>,
    val pastureafintake: List<String?>,
    val pastureconservation: List<String?>,
    val totalmilkproduction: List<String?>,
    val milksolids: List<String?>,
    val production : ProductionSummary ? = null,
    val drymatterintakemlkcows: List<String?>,
    val pastureintakemlkcows: List<String?>,
    val silageintakemlkcows: List<String?>,
    val supplementintakemlkcows: List<String?>,

    val drymatterintakeyngstock: List<String?>,
    val pastureintakeyngstock: List<String?>,
    val silageintakeyngstock: List<String?>,
    val supplementintakeyngstock: List<String?>,

    val silageintakeall: List<String?>,
    val pastureusage: List<String?>,
    val silageusage: List<String?>,
    val supplementintake: List<String?>,
//    val dmusagesupplementintake: List<String?>,
    val supplementusage: List<String?>,

    val feedgroups: List<FeedGroup>,
    val FeedUsage: List<FeedUsageEntry>,
    val Replacement: List<String>,

    val ElapsedTimeFormulation: String,
    val ElapsedTimeReporting: String,
    val ElapsedTimeTotal: String
)

@Serializable
data class FeedGroup(
    val group: Int,
    val mlk_dmusage: Double?,
    val yst_dmusage: Double?,
    val mlk_afusage: Double?,
    val yst_afusage: Double?,
    val mlk_dmintake: Double?,
    val yst_dmintake: Double?,
    val mlk_afintake: Double?,
    val yst_afintake: Double?,
    val mlk_cost: Double?,
    val yst_cost: Double?,
    val all_dmusage: Double?,
    val all_afusage: Double?,
    val all_dmintake: Double?,
    val all_afintake: Double?,
    val all_cost: Double?
)

@Serializable
data class FeedUsageEntry(
    val ingredient: String,
    val totalAF: Double?,
    val totalDM: Double?,
    val totalValue: Double?
)
