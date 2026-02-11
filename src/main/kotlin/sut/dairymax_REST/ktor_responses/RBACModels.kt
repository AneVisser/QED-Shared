package qed.testbaseclass.sut.dairymax_REST.datamodels

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val username: String,
    val email: String,
    val roles: List<String>,
    val permissions: List<String> = emptyList()  // Changed from Set to List
)

@Serializable
data class UserRole(
    val id: Int,
    val name: String,
    val permissions: List<String>
)

@Serializable
data class UserPermission(
    val id: Int,
    val name: String,
    val description: String
)

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String,
    val user: User,
    val menuItems: List<MenuItem>
)

@Serializable
data class MenuItem(
    val id: String,
    val label: String,
    val path: String,
    val requiredPermission: String,
    val icon: String? = null
)