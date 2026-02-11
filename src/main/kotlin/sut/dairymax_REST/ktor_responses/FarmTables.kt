package qed.testbaseclass.sut.dairymax_REST.ktor_responses

// ============================================================================
// FARM MODEL - Data Class
// ============================================================================

import kotlinx.serialization.Serializable

/**
 * Farm data model.
 * Maps to: dmfarms table
 */
@Serializable
data class Farm(
    val id: Int,                    // farmid
    val farmName: String,            // farmname
    val address1: String? = null,    // address1
    val address2: String? = null,    // address2
    val town: String? = null,        // town
    val region : String? = null,
    val area : String? = null,
    val postalCode: String? = null,  // postalcode
    val hectares: Double? = null,    // hectares
    val supplierCode: String? = null, // suppliercode
    val companyId : Int? = null
)

/**
 * Response when fetching user's farms.
 * Includes default farm for auto-selection.
 */
@Serializable
data class FarmListResponse(
    val farms: List<Farm>,
    val defaultFarmId: Int?  // ID of default farm to auto-select
)

