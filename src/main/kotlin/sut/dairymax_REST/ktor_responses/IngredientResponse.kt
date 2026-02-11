package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import kotlinx.serialization.Serializable

// In shared-models/src/main/kotlin/models/responses/IngredientResponses.kt
@Serializable
data class IngredientListResponse(
    val ingredients: List<IngredientListItemDTO>,
    val total: Int,
    val limit: Int,
    val offset: Int
)

@Serializable
data class IngredientListItemDTO(
    val ingId: Int,
    val ingCode: String,
    val ingDescrip: String,
    val company_id: Int?,
    val available: Boolean,
    val cost: Double?,
    val dryMatter: Double?,
    val entryDryMatter: Double?,
    @Transient
    val shouldDelete: Boolean = false  // Internal flag for filtering
)