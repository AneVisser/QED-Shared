package qed.testbaseclass.sut.dairymax_REST.cow_model

import kotlinx.serialization.Serializable
import qed.sut.dairymax_REST.FunctionResponse
import qed.sut.dairymax_REST.MineralRequirement
import qed.sut.dairymax_REST.Requirement

@Serializable
data class DelphiRequirement(
    val Maintenance: List<Double>,
    val Growth: List<Double>,
    val Production: List<List<Double>> // note: Production can be nested arrays
)

@Serializable
data class DelphiMineralRequirement(
    val Maintenance: List<Double>,
    val Growth: List<Double>,
    val Production: List<List<Double>>,
    val Maximum: List<Double?>
)

@Serializable
data class DelphiLinProgParametersResponse(
    val id: String,
    val function: String,
    val NumProdPortions: Int,
    val NumPeriods: Int,
//    val NumLactPeriods: Int,                    // NEW: needed to know which periods have production
    val MEPerKgLipidLoss: Double,
    val MEPerKgLipidGain: Double,
    val BodyFatMEPerKg: Double,
    val prodPortions: List<List<Double>>,
    val DMI: List<Double>,
    val MaxFIC: List<Double>,
    val MERequirement: DelphiRequirement,
    val ProteinRequirement: DelphiRequirement,
    val MineralRequirements: Map<String, DelphiMineralRequirement>,
    val LWTInPeriod: List<Double>,
    val MilkPricePerKg: Double,
    // Total requirements at maximum genetic potential
    val maxProdMERequirement: List<Double>? = null,  // sum(Production portions)
    val maxProdProteinRequirement: List<Double>? = null,
    val maxProdMineralRequirements: Map<String, List<Double>>? = null
)