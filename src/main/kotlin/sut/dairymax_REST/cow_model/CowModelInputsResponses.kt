package qed.testbaseclass.sut.dairymax_REST.cow_model

import kotlinx.serialization.Serializable
import qed.sut.dairymax_REST.CowInput
import qed.sut.dairymax_REST.LinProgParametersResponse
import qed.sut.dairymax_REST.PaymentSystemInput


@Serializable
data class CowModelRequest(
    val cow: CowInput,
    val paymentSystem: PaymentSystemInput? = null
)

@Serializable
data class CowModelResponse(
    val linProgParameters: LinProgParametersResponse,
    val lactationCurves: LactationCurves?,
    val milkPricePerKg: Double,
    val milkPricePerKgMS: Double,
    val milkPriceConversionFactor: Double,
    val matureWeight: Int,
)

@Serializable
data class LactationCurves(
    val days: Int,               // Number of days (305 standard)
    val milk: List<Double>,      // kg milk/day for each day
    val fat: List<Double>,       // kg fat/day for each day
    val protein: List<Double>    // kg protein/day for each day
)

// Note: LinProgParametersResponse should already exist in your codebase

// ============================================================================
// Simple DTOs for dropdown lists (wrapping enum values for JSON)
// ============================================================================

@Serializable
data class BreedInfo(
    val id: Int,           // Ordinal for sorting/display
    val name: String,      // Display name ("Friesian")
    val enumName: String   // Enum name for API calls ("brFriesian")
)

@Serializable
data class ContourInfo(
    val id: Int,           // Ordinal for sorting/display
    val name: String,      // Display name ("Easy rolling")
    val enumName: String   // Enum name for API calls ("ctRolling")
)

@Serializable
data class MineralInfo(val id: Int, val symbol: String)

@Serializable
data class BreedMilkDefaults(
    val enumName: String,
    val name: String,
    val fat: Double,
    val protein: Double,
    val lactose: Double
)