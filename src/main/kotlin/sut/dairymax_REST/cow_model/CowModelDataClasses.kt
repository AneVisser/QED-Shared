package qed.sut.dairymax_REST

import kotlinx.serialization.Serializable

@Serializable
enum class Contour(val value : String, val walkingCostPerKm : Double) {
    FLAT( "Flat", 2.0),
    ROLLING( "Rolling", 3.0),
    STEEP( "Steep", 6.0)
    ;
    companion object {
        fun fromOrdinal(ord: Int?): Contour? = ord?.let { Contour.entries.getOrNull(it) } }

}

@Serializable
enum class Breed(val value : String, val milkFat : Double, val milkProtein : Double, val milkLactose : Double) {
    JERSEY("Jersey", 5.7, 4.1, 4.90),
    KIWICROSS("Kiwicross", 4.9, 3.8, 4.88),
    FRIESIAN("Friesian", 4.4, 3.6, 4.85),
    AYRSHIRE("Ayrshire", 4.4, 3.6, 4.44),
    HOLSTEIN("Holstein", 4.33, 3.52, 4.71)
    ;
    companion object {
         fun fromOrdinal(ord: Int?): Breed? = ord?.let { entries.getOrNull(it) } }
    // values for Lactose from Nick Sneddon, fat and protein from DairyNZ (for Holstein from CRV )
}


@Serializable
data class CowInput(
    val breed: Breed,               // Breed (JERSEY, KIWICROSS, FRIESIAN, AYRSHIRE, HOLSTEIN)
    val matureWeight: Int,          // mature live weight (kg)
    val avgDistance: Int,           // average walking distance per day
    val contour: Contour,           // coutour (FLAT, ROLLING, STEEP)
    val parity: Int,                // parity number (1..4)
    val calvingInterval: Int,       // number of days between calcings
    val genotypeAdj: Int,           // maximum production level in kg/standard lactation
    val numDaysDry: Int,            // number of dry days in a calving cycle
    val milkFat: Double,            // % milkfat
    val milkProt: Double,           // % milk protein
    val milkLactose: Double,        // % milk lactose
    val ageInMonths: Int            // age in months
)

@Serializable
data class PaymentSystemInput(
    val fat : Double = 0.0,
    val protein : Double = 0.0,
    val casein : Double = 0.0,
    val albumin : Double = 0.0,
    val lactose : Double = 0.0,
    val volume : Double = 0.0,
)

@Serializable
data class KgMS (
    val kgms : Double
)

@Serializable
data class BreedMilk (
    val kgmilk : Double
)

@Serializable
data class ReplValue (
    val replacementvalue : Double
)

@Serializable
data class Requirement(
    val Maintenance: List<Double>,              // [numPeriods] - varies by period
    val Growth: List<Double>,                   // [numPeriods] - varies by period
    val RequirementPerPortion: List<Double>      // [numProdPortions] - same for all lactation periods
)

@Serializable
data class MineralRequirement(
    val Maintenance: List<Double>,              // [numPeriods]
    val Growth: List<Double>,                   // [numPeriods]
    val RequirementPerPortion: List<Double>,     // [numProdPortions] - same for all lactation periods
    val Maximum: List<Double?>                  // [numPeriods]
)

@Serializable
sealed class FunctionResponse {
    abstract val id: String
    abstract val function: String
}

@Serializable
data class CowParametersResponse(
    override val id: String,
    override val function: String,
    val CowParameters: CowInput
) : FunctionResponse()

@Serializable
data class BreedNameResponse(
    override val id: String,
    override val function: String,
    val BreedName: String
) : FunctionResponse()

@Serializable
data class MjPerKgOfBreedMilkResponse(
    override val id: String,
    override val function: String,
    val MJperKGOfBreedMilk: Double
) : FunctionResponse()

@Serializable
data class KgBreedMilk2MSResponse(
    override val id: String,
    override val function: String,
    val kgBreedMilk2MS: Double
) : FunctionResponse()

@Serializable
data class Ms2KgBreedMilkResponse(
    override val id: String,
    override val function: String,
    val MS2kgBreedMilk: Double
) : FunctionResponse()

@Serializable
data class BrdPercMilkFatResponse(
    override val id: String,
    override val function: String,
    val BrdPercMilkFat: Double
) : FunctionResponse()

@Serializable
data class BrdPercMilkProtResponse(
    override val id: String,
    override val function: String,
    val BrdPercMilkProt: Double
) : FunctionResponse()

@Serializable
data class BrdPercMilkLactoseResponse(
    override val id: String,
    override val function: String,
    val BrdPercMilkLactose: Double
) : FunctionResponse()

@Serializable
data class BrdPercMilkCaseinResponse(
    override val id: String,
    override val function: String,
    val BrdPercMilkCasein: Double
) : FunctionResponse()

@Serializable
data class BrdPercMilkAlbuminResponse(
    override val id: String,
    override val function: String,
    val BrdPercMilkAlbumin: Double
) : FunctionResponse()

@Serializable
data class PaymentSystemResponse(
    override val id: String,
    override val function: String,
    val PaymentSystem: PaymentSystemInput
) : FunctionResponse()

@Serializable
data class ValueBwtChangeResponse(
    override val id: String,
    override val function: String,
    val ValueBwtChange: Double
) : FunctionResponse()

@Serializable
data class MilkPricePerKgResponse(
    override val id: String,
    override val function: String,
    val MilkPricePerKg: Double
) : FunctionResponse()

@Serializable
data class MilkPriceConversionFactorResponse(
    override val id: String,
    override val function: String,
    val MilkPriceConversionFactor: Double
) : FunctionResponse()

@Serializable
data class MatureWeightResponse(
    override val id: String,
    override val function: String,
    val MatureWeight: Int
) : FunctionResponse()

@Serializable
data class DairyCowPeriodsResponse(
    override val id: String,
    override val function: String,
    val NumPeriods: Int,
    val NumLactPeriods: Int,
    val NumDryPeriods: Int
) : FunctionResponse()

@Serializable
data class LinProgParametersResponse(
    override val id: String,
    override val function: String,
    val NumProdPortions: Int,
    val NumPeriods: Int,
    val NumLactPeriods: Int,                    // NEW: needed to know which periods have production
    val MEPerKgLipidLoss: Double,
    val MEPerKgLipidGain: Double,
    val kgDailyLipidChange: List<Double>,
    val kgPerBCS_1_5_Point: List<Double>,
    val prodPortionSizes: List<Double>,         // CHANGED: [numPeriods] - portion size per period (was 2D matrix)
    val DMI: List<Double>,
    val MaxFIC: List<Double>,
    val MERequirement: Requirement,
    val ProteinRequirement: Requirement,
    val MineralRequirements: Map<String, MineralRequirement>,
    val LWTInPeriod: List<Double>,
) : FunctionResponse() {

    /**
     * Compute total production ME requirement for a period.
     * Sum of all ProductionPerPortion values for lactation periods, 0 for dry periods.
     */
    fun maxProdMERequirement(period: Int): Double {
        return if (period < NumLactPeriods) {
            MERequirement.RequirementPerPortion.sum()
        } else {
            0.0
        }
    }

    /**
     * Compute total production protein requirement for a period.
     */
    fun maxProdProteinRequirement(period: Int): Double {
        return if (period < NumLactPeriods) {
            ProteinRequirement.RequirementPerPortion.sum()
        } else {
            0.0
        }
    }

    /**
     * Compute total production mineral requirement for a period.
     */
    fun maxProdMineralRequirement(mineral: String, period: Int): Double {
        val mineralReq = MineralRequirements[mineral] ?: return 0.0
        return if (period < NumLactPeriods) {
            mineralReq.RequirementPerPortion.sum()
        } else {
            0.0
        }
    }
}


@Serializable
data class ContoursResponse(
    val Contours: List<String>
)

@Serializable
data class MineralsResponse(
    val function: String,
    val Minerals: List<String>
)

@Serializable
data class BreedsResponse(
    val function: String,
    val Breeds: List<String>
)

