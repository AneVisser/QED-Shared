package qed.testbaseclass.sut.dairymax_REST.routes

import qed.testbaseclass.RequestType


enum class RouteGroup(
    val baseRoute: String,
    val requirement: PermissionRequirement
) {
    // Public routes
    AUTHENTICATE("api/auth", PermissionRequirement.None),

    // Single permission routes
    CALCULATE("api/calculate", PermissionRequirement.Single(Permission.CALCULATE_FEED)),
    FARMS("/api/farms",  PermissionRequirement.Single(Permission.SELECT_FARM) ),
    INGREDIENT("api/ingredients", PermissionRequirement.Single(Permission.VIEW_INGREDIENTS)),
    MANAGEMENT("api/manage", PermissionRequirement.Single(Permission.ADD_FARM)),
    ORDER("api/order", PermissionRequirement.Single(Permission.ORDER)),
    PRODUCTSLIST("api/productslist", PermissionRequirement.Single(Permission.CALCULATE_FEED)),
    PRODUCTSLISTSCLIENTS("api/productslistsclients", PermissionRequirement.Single(Permission.MANAGE_CATTLE)),
    RBAC("api/rbac", PermissionRequirement.Single(Permission.ADMIN)),
    PAGES("api/pages", PermissionRequirement.Single(Permission.EDIT_FARM)),
    RESPONSECURVES("api/responsecurves", PermissionRequirement.Single(Permission.ADMIN)),
    SOLUTION("api/solution", PermissionRequirement.Single(Permission.CALCULATE_FEED)),
    TREE("api/tree", PermissionRequirement.Single(Permission.CALCULATE_FEED)),
    USER("api/user", PermissionRequirement.AnyOf(setOf(Permission.EDIT_FARM, Permission.SELECT_FARM))),
    LOCATION("api/locations", PermissionRequirement.Single(Permission.EDIT_FARM)),
    ADMIN("api/admin", PermissionRequirement.Single(Permission.ADMIN)),
    DOCS("docs", PermissionRequirement.Single(Permission.ADMIN)),
    COMPANY("api/companies", PermissionRequirement.Single(Permission.VIEW_COMPANIES)),
    COW_MODEL("api/cowmodel", PermissionRequirement.Single(Permission.SELECT_FARM)),
    LP_MODEL("api/lpmodel", PermissionRequirement.Single(Permission.ADMIN)),

    // Example of flexible permissions (if needed)
    // REPORTS("api/reports", PermissionRequirement.AnyOf(setOf(Permission.VIEW_REPORTS, Permission.ADMIN))),

    TESTING("dev", PermissionRequirement.None),
    UNDEFINED("Undefined", PermissionRequirement.None);

    val basePath: String get() = "/$baseRoute"
}


