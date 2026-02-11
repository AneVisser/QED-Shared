package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import kotlinx.serialization.Serializable

// UserSetupState.kt
@Serializable
data class UserSetupState(
    val hasCompany: Boolean,
    val companyId: Int?,
    val farmCount: Int,
    val canCreateFarm: Boolean,
    val needsOnboarding: Boolean,
    val maxFarmsReached: Boolean
)


// CreateCompanyRequest.kt (simplified for users)
@Serializable
data class CreateUserCompanyRequest(
    val companyName: String
) {
    fun validate() {
        require(companyName.isNotBlank()) { "Company name is required" }
        require(companyName.length in 2..100) { "Company name must be 2-100 characters" }
    }
}

/**
 * Request model for creating a farm.
 * Updated to use location hierarchy (country -> region -> area).
 */
@Serializable
data class CreateFarmRequest(
    val farmName: String,
    val companyId: Int,
    val countryId: Int,                 // Required - from dmcountry
    val regionId: Int,                  // Required - from dmregion
    val areaId: Int,                    // Required - from dmarea
    val hectares: Double,               // Required
    val address1: String? = null,       // Optional
    val address2: String? = null,       // Optional
    val town: String? = null,           // Optional - kept for compatibility
    val postalCode: String? = null,     // Optional
    val supplierCode: String? = null    // Optional
) {
    fun validate() {
        require(farmName.isNotBlank()) { "Farm name is required" }
        require(farmName.length in 2..100) { "Farm name must be 2-100 characters" }
//        require(town.isNotBlank()) { "Town/Region is required" }
//        require(town.length <= 100) { "Town/Region too long" }
        require(hectares > 0) { "Hectares must be greater than 0" }
        require(hectares <= 100000) { "Hectares seems unreasonably large - please contact support" }
    }
}