package qed.testbaseclass.sut.dairymax_REST.ktor_datamodels

import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationResponse(
    val enforcePermissions: Boolean,
    val enforceAuthentication: Boolean,
    val environment: String
)