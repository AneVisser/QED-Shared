package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import kotlinx.serialization.Serializable

@Serializable
data class UpdateFarmRequest(
    val farmName: String,
    val companyId: Int? = null,
    val address1: String? = null,
    val address2: String? = null,
    val town: String? = null,
    val postalCode: String? = null,
    val hectares: Int? = null,
    val supplierCode: String? = null
)
