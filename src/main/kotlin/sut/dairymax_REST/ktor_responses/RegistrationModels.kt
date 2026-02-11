package qed.testbaseclass.sut.dairymax_REST.datamodels

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val username: String,
    val email: String,
    val password: String,
    val captchaToken: String? = null
)

@Serializable
data class RegistrationResponse(
    val success: Boolean,
    val message: String,
    val userId: Int? = null
)

@Serializable
data class VerificationResponse(
    val success: Boolean,
    val message: String
)

@Serializable
data class SuccessResponse(
    val success: Boolean,
)
