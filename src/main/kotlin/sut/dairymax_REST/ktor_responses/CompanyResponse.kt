package qed.testbaseclass.sut.dairymax_REST.ktor_datamodels

import kotlinx.serialization.Serializable

/**
 * Response model for company hierarchy
 */
@Serializable
data class CompanyResponse(
    val companyId: Int,
    val hierarchy: List<Int>,
    val hierarchyDetails: String
)

@Serializable
data class Company(
    val companyId: Int,
    val companyName: String,
    val parentId: Int?
)

@Serializable
data class CreateCompanyRequest(
    val companyName: String,
    val parentId: Int?
)

@Serializable
data class UpdateCompanyRequest(
    val companyName: String,
    val parentId: Int?
)