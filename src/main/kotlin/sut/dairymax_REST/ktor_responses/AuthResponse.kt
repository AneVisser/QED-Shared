package qed.testbaseclass.sut.dairymax_REST.datamodels

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val userId: Int,
    val username: String,
    val permissions: List<String>,
    val roles: List<String>
)

@Serializable
data class InvalidAuthResponse(
    val error: String,
)