package qed.testbaseclass.sut.dairymax_REST.routes

sealed class PermissionRequirement {
    data class Single(val permission: Permission) : PermissionRequirement()
    data class AnyOf(val permissions: Set<Permission>) : PermissionRequirement()
    data class AllOf(val permissions: Set<Permission>) : PermissionRequirement()
    object None : PermissionRequirement()

    fun check(userPermissions: Set<Permission>): Boolean {
        return when (this) {
            is Single -> userPermissions.contains(permission)
            is AnyOf -> permissions.any { userPermissions.contains(it) }
            is AllOf -> permissions.all { userPermissions.contains(it) }
            is None -> true
        }
    }
}