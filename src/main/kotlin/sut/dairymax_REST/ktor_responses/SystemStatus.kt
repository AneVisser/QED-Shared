package qed.testbaseclass.sut.dairymax_REST.ktor_datamodels

import kotlinx.serialization.Serializable

@Serializable
data class SystemStatusResponse(
    val permissionEnforcement: Boolean,
    val environment: String
)

@Serializable
data class DeploymentCheck(
    val name: String,
    val status: String,
    val value: String,  // Convert everything to String
    val expected: String
)

@Serializable
data class DeploymentCheckResponse(
    val environment: String,
    val checks: List<DeploymentCheck>,
    val allPassed: Boolean
)

@Serializable
data class EnvironmentResponse(
    val environment: String,
    val enforceAuthentication: Boolean?,
    val enforcePermissions: Boolean?,
    val version: String
)