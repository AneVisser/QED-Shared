package qed.testbaseclass.sut.dairymax_REST.ktor_datamodels

import kotlinx.serialization.Serializable

@Serializable
data class PasswordResetRequest(
    val email: String
)

@Serializable
data class PasswordResetResponse(
    val success: Boolean,
    val message: String
)

@Serializable
data class PasswordResetConfirm(
    val token: String,
    val newPassword: String
)