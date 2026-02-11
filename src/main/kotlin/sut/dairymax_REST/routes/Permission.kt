package qed.testbaseclass.sut.dairymax_REST.routes

enum class Permission(
    var id: Int? = null,
    var description: String? = null
) {
    CALCULATE_FEED,
    VIEW_INGREDIENTS,
    EDIT_INGREDIENTS,
    EDIT_FARM,
    ADD_FARM,
    SELECT_FARM,
    MANAGE_USERS,
    ORDER,
    MAINTAIN_USERS,
    VIEW_REPORTS,
    MANAGE_SETTINGS,
    VIEW_NUTRITION,
    MANAGE_CATTLE,
    ADMIN,
    VIEW_COMPANIES
}