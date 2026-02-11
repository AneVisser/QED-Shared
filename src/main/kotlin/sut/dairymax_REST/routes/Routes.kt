package qed.testbaseclass.sut.dairymax_REST.routes

import qed.testbaseclass.RequestType

/**
 * Centralized definition of all application routes.
 * Each route belongs to a RouteGroup and can optionally override the group's permission requirements.
 */
enum class Routes(
    val group: RouteGroup,
    val path: String,
    val method: RequestType,
    val permissionOverride: PermissionRequirement? = null,
    val requiresAuth: Boolean = true    //(most routes need auth)
) {

    // ============================================
    // AUTH Routes
    // Note: Some routes are public (login), others require JWT (me, menu)
    // ============================================

    // Public auth routes (no JWT required)
    AUTH_LOGIN(RouteGroup.AUTHENTICATE, "login", RequestType.POST, requiresAuth = false),
    AUTH_ME(RouteGroup.AUTHENTICATE, "me", RequestType.GET),
    AUTH_MENU(RouteGroup.AUTHENTICATE, "menu", RequestType.GET),


    // ============================================
    // PASSWORD RESET Routes (all public - no auth required)
    // ============================================
    PASSWORD_RESET_REQUEST(RouteGroup.AUTHENTICATE, "password-reset/request", RequestType.POST,
        requiresAuth = false),  // Public - request reset link
    PASSWORD_RESET_VERIFY(RouteGroup.AUTHENTICATE, "password-reset/verify", RequestType.GET,
        requiresAuth = false),  // Public - verify token validity
    PASSWORD_RESET_CONFIRM(RouteGroup.AUTHENTICATE, "password-reset/confirm", RequestType.POST,
        requiresAuth = false),  // Public - complete password reset


    // ============================================
    // REGISTRATION Routes
    // Mix of public (register, verify) and protected (admin) routes
    // ============================================
    // Public registration routes (no JWT required)
    AUTH_REGISTER(RouteGroup.AUTHENTICATE, "register", RequestType.POST, requiresAuth = false),
    AUTH_VERIFY_EMAIL(RouteGroup.AUTHENTICATE, "verify-email", RequestType.GET, requiresAuth = false),

    // ============================================
    // FARM Routes
    // ============================================
    GET_MY_FARMS(RouteGroup.FARMS, "my-farms", RequestType.GET),
    GET_FARM_BY_ID(RouteGroup.FARMS, "{farmId}", RequestType.GET),
    FARM_GET_ALL(RouteGroup.ADMIN, "farms", RequestType.GET),
    FARM_GET_BY_ID(RouteGroup.ADMIN, "farms/{id}", RequestType.GET),
    FARM_CREATE(RouteGroup.ADMIN, "farms", RequestType.POST),
    FARM_UPDATE(RouteGroup.ADMIN, "farms/{id}", RequestType.PUT),
    FARM_DELETE(RouteGroup.ADMIN, "farms/{id}", RequestType.DELETE),

    // ============================================
    // USER ONBOARDING Routes
    // ============================================
    USER_SETUP_COMPANY(RouteGroup.USER, "setup/company", RequestType.POST, permissionOverride = PermissionRequirement.Single(Permission.SELECT_FARM)),
    USER_SETUP_FARM(RouteGroup.USER, "setup/farm", RequestType.POST, permissionOverride = PermissionRequirement.Single(Permission.SELECT_FARM)),
    USER_SETUP_STATE(RouteGroup.USER, "setup-state", RequestType.GET, permissionOverride = PermissionRequirement.Single(Permission.SELECT_FARM)),

    // ============================================
    // LOCATIONS Routes
    // ============================================
    LOCATIONS_COUNTRIES(RouteGroup.LOCATION, "countries", RequestType.GET, permissionOverride = PermissionRequirement.Single(Permission.SELECT_FARM)),
    LOCATIONS_REGIONS(RouteGroup.LOCATION, "countries/{countryId}/regions", RequestType.GET, permissionOverride = PermissionRequirement.Single(Permission.SELECT_FARM)),
    LOCATIONS_AREAS(RouteGroup.LOCATION, "countries/{countryId}/regions/{regionId}/areas", RequestType.GET, permissionOverride = PermissionRequirement.Single(Permission.SELECT_FARM)),

    // ============================================
    // CALCULATE Routes
    // ============================================
    CALCULATE_FORMULATE(RouteGroup.CALCULATE, "formulate/{farmId}", RequestType.POST),
    CALCULATE_GET_SOLUTION(RouteGroup.CALCULATE, "solution/{farmId}", RequestType.GET),

    CALCULATE_FARMUPDATE(RouteGroup.CALCULATE, "{farmId}/updatefarm", RequestType.PUT),
    CALCULATE_GET_DATA(RouteGroup.CALCULATE, "{farmId}", RequestType.GET),
    CALCULATE_GET_PASTURE_DATA(RouteGroup.CALCULATE, "{farmId}/pasture", RequestType.GET),
    CALCULATE_GET_CALVING_PATTERN(RouteGroup.CALCULATE, "{farmId}/calving", RequestType.GET),
    CALCULATE_GET_FARM_PARAMETERS(RouteGroup.CALCULATE, "{farmId}/parameters", RequestType.GET),
    CALCULATE_GET_PRODUCTION_GROUPS(RouteGroup.CALCULATE, "{farmId}/production-groups", RequestType.GET),
    CALCULATE_GET_YOUNGSTOCK_GROUPS(RouteGroup.CALCULATE, "{farmId}/youngstock", RequestType.GET),
    CALCULATE_GET_GRAZED_CROPS(RouteGroup.CALCULATE, "{farmId}/grazed-crops", RequestType.GET),
    CALCULATE_GET_SUPPLEMENTS(RouteGroup.CALCULATE,"{farmId}/supplements",RequestType.GET),
    CALCULATE_GET_FEED_BUNKERS(RouteGroup.CALCULATE, "{farmId}/feedbunkers", RequestType.GET),
    CALCULATE_GET_FEED_BUNKER_CROPS(RouteGroup.CALCULATE, "{farmId}/feedbunkercrops", RequestType.GET),
    // save data
    CALCULATE_SAVE_PASTURE_DATA(RouteGroup.CALCULATE, "/{farmId}/pasture", RequestType.POST),
    CALCULATE_SAVE_CALVING_PATTERN(RouteGroup.CALCULATE, "/{farmId}/calving", RequestType.POST),
    CALCULATE_SAVE_FARM_PARAMETERS(RouteGroup.CALCULATE, "/{farmId}/parameters", RequestType.POST),
    CALCULATE_SAVE_PRODUCTION_GROUPS(RouteGroup.CALCULATE, "/{farmId}/production-groups", RequestType.POST),
    CALCULATE_SAVE_YOUNGSTOCK_GROUPS(RouteGroup.CALCULATE, "/{farmId}/youngstock", RequestType.POST),
    CALCULATE_SAVE_GRAZED_CROPS(RouteGroup.CALCULATE, "/{farmId}/grazed-crops", RequestType.POST),
    CALCULATE_SAVE_SUPPLEMENTS(RouteGroup.CALCULATE, "/{farmId}/supplements", RequestType.POST),
    CALCULATE_SAVE_FEED_BUNKERS(RouteGroup.CALCULATE, "/{farmId}/feed-bunkers", RequestType.POST),
    CALCULATE_SAVE_FEED_BUNKER_CROPS(RouteGroup.CALCULATE, "/{farmId}/feed-crops", RequestType.POST),


    // ============================================
    // SOLUTION Routes (from SolutionHandlers)
    // ============================================
    SOLUTION_VIEW(RouteGroup.SOLUTION, "view/{solutionNr}", RequestType.GET),
    SOLUTION_GET_LIST(RouteGroup.SOLUTION, "getSolutionsList/{userId}", RequestType.GET),
    SOLUTION_DELETE_REPORTS(RouteGroup.SOLUTION, "deleteReports", RequestType.POST),

    // ============================================
    // SYSTEM Routes (Health checks, status)
    // ============================================
    SYSTEM_STATUS(RouteGroup.AUTHENTICATE, "system/status", RequestType.GET),
    SYSTEM_DEPLOYMENT_CHECK(RouteGroup.AUTHENTICATE, "system/deployment-check", RequestType.GET, PermissionRequirement.Single(
        Permission.ADMIN)),
    SYSTEM_ENVIRONMENT(RouteGroup.AUTHENTICATE, "environment", RequestType.GET, requiresAuth = false),

    // ============================================
    // INGREDIENTS Routes
    // ============================================
    // Feed Composition & Master Data
    INGREDIENT_GET_COMPOSITION(RouteGroup.INGREDIENT, "{ingId}/composition", RequestType.GET),
    INGREDIENT_GET_DESCRIPTION(RouteGroup.INGREDIENT, "{ingId}/description", RequestType.GET),
    INGREDIENT_LIST(RouteGroup.INGREDIENT,"", RequestType.GET),
    INGREDIENT_SAVE(RouteGroup.INGREDIENT, "{ingId}", RequestType.PUT),
    INGREDIENT_CREATE(RouteGroup.INGREDIENT, "", RequestType.POST),
    INGREDIENT_DELETE(RouteGroup.INGREDIENT, "{ingId}", RequestType.DELETE),
    INGREDIENT_RESET(RouteGroup.INGREDIENT, "{ingId}/reset", RequestType.POST),
    INGREDIENT_NUTRIENT_TEMPLATE(RouteGroup.INGREDIENT, "nutrients/template", RequestType.GET),



    // ============================================
    // COMPANY Routes
    // ============================================
    COMPANY_GET_HIERARCHY(RouteGroup.COMPANY, "{companyId}/hierarchy", RequestType.GET),
    COMPANY_GET_ALL(RouteGroup.ADMIN,"companies", RequestType.GET),
    COMPANY_GET_BY_ID(RouteGroup.ADMIN,"companies/{id}", RequestType.GET,
        PermissionRequirement.AnyOf(
            setOf(Permission.SELECT_FARM,
                               Permission.ADMIN)
    )),
    COMPANY_CREATE(RouteGroup.ADMIN,"companies", RequestType.POST),
    COMPANY_UPDATE(RouteGroup.ADMIN,"companies/{id}", RequestType.PUT),
    COMPANY_DELETE(RouteGroup.ADMIN,"companies/{id}", RequestType.DELETE),

    // INGREDIENTS_GET_MASTER_DATA(RouteGroup.INGREDIENTS, "getmasterdata/{farmId}", RequestType.GET),
    // INGREDIENTS_GET_GROUP_LIST(RouteGroup.INGREDIENTS, "getgrouplist/{farmId}", RequestType.GET),
    // INGREDIENTS_GET_CONST_UNITS(RouteGroup.INGREDIENTS, "getingconstunits", RequestType.GET),

    // Lists & Queries
    // INGREDIENTS_GET_ALL_LIST(RouteGroup.INGREDIENTS, "getallingredientslist/{farmId}", RequestType.GET),
    // INGREDIENTS_INDEX(RouteGroup.INGREDIENTS, "index/{farmId}", RequestType.GET),  // List view
    // INGREDIENTS_INDEX_DEFAULT(RouteGroup.INGREDIENTS, "index", RequestType.GET),    // Default list

    // Pricing
    // INGREDIENTS_GET_ALL_PRICES(RouteGroup.INGREDIENTS, "getallprices/{farmId}", RequestType.GET),
    // INGREDIENTS_GET_PRICE(RouteGroup.INGREDIENTS, "getprice/{ingredientId}", RequestType.GET),

    // CRUD Operations
    // INGREDIENTS_NEW(RouteGroup.INGREDIENTS, "new/{farmId}", RequestType.GET),
    // INGREDIENTS_SAVE_NEW(RouteGroup.INGREDIENTS, "savenew/{farmId}", RequestType.POST),
    // INGREDIENTS_SAVE(RouteGroup.INGREDIENTS, "save/{ingredientId}", RequestType.POST),
    // INGREDIENTS_SAVE_INGREDIENT(RouteGroup.INGREDIENTS, "saveingredient/{farmId}", RequestType.POST),
    // INGREDIENTS_UPDATE(RouteGroup.INGREDIENTS, "updateingredient/{ingredientId}", RequestType.PUT),
    // INGREDIENTS_DELETE(RouteGroup.INGREDIENTS, "deleteingredient/{ingredientId}", RequestType.DELETE),

    // Ownership & Permissions
    // INGREDIENTS_GET_OWNERSHIP(RouteGroup.INGREDIENTS, "getownership/{ingredientId}", RequestType.GET),

    // Nutrient Calculations
    // INGREDIENTS_RECALC_NUTRIENTS(RouteGroup.INGREDIENTS, "recalcNutrients", RequestType.POST),
    // INGREDIENTS_RECALC_NUTRIOPT(RouteGroup.INGREDIENTS, "recalcNutriopt", RequestType.POST),

    // ============================================
    // INFO Routes (Static content pages)
    // Previously: PagesHandlers - renamed to InfoRoutes
    // ============================================
    // INFO_HOME(RouteGroup.PAGES, "home", RequestType.GET),
    // INFO_ABOUT(RouteGroup.PAGES, "about", RequestType.GET),
    // INFO_LINKS(RouteGroup.PAGES, "links", RequestType.GET),
    // INFO_CONTACT(RouteGroup.PAGES, "contact", RequestType.GET),
    // INFO_ARTICLES(RouteGroup.PAGES, "articles", RequestType.GET),
    // INFO_ARTICLE_DETAIL(RouteGroup.PAGES, "articles/{articleId}", RequestType.GET),
    // INFO_SEND_EMAIL(RouteGroup.PAGES, "email", RequestType.POST),



    // ============================================
    // using kTor model
    // ============================================
    COWMODEL_GETCONTOURS(RouteGroup.COW_MODEL,"getcontours", RequestType.GET),
    COWMODEL_GETBREEDS(RouteGroup.COW_MODEL,"getbreeds", RequestType.GET),
    COWMODEL_GETMINERALS(RouteGroup.COW_MODEL,"getminerals", RequestType.GET),
    COWMODEL_GETBREEDDEFAULTS(RouteGroup.COW_MODEL,"getbreeddefaults", RequestType.GET),
    COWMODEL_CALCULATE(RouteGroup.COW_MODEL,"calculate", RequestType.POST, permissionOverride = PermissionRequirement.Single(Permission.ADMIN)),


    // ============================================
    // TREE/NAVIGATION Routes
    // Legacy UI for selecting previous model runs/reports
    // ============================================
    // TREE_VIEW(RouteGroup.TREE, "view", RequestType.GET),
    // TREE_GRID_VIEW(RouteGroup.TREE, "grid/view", RequestType.GET),
    // TREE_GET_NODES(RouteGroup.TREE, "nodes", RequestType.GET),
    // TREE_GET_REPORTS(RouteGroup.TREE, "reports", RequestType.GET),


    // ============================================
    // ADMIN Routes (User & Role Management)
    // All routes require ADMIN permission
    // ============================================

    // User Management
    ADMIN_USERS_LIST(RouteGroup.ADMIN, "users", RequestType.GET),
    ADMIN_USER_GET(RouteGroup.ADMIN, "users/{id}", RequestType.GET),
    ADMIN_USER_CREATE(RouteGroup.ADMIN, "users", RequestType.POST),
    ADMIN_USER_UPDATE(RouteGroup.ADMIN, "users/{id}", RequestType.PUT),
    ADMIN_USER_DELETE(RouteGroup.ADMIN, "users/{id}", RequestType.DELETE),
    ADMIN_USER_ASSIGN_ROLE(RouteGroup.ADMIN, "users/{id}/roles", RequestType.POST),
    ADMIN_USER_REMOVE_ROLE(RouteGroup.ADMIN, "users/{id}/roles/{roleId}", RequestType.DELETE),

    // Role Management
    ADMIN_ROLES_LIST(RouteGroup.ADMIN, "roles", RequestType.GET),
    ADMIN_ROLE_GET(RouteGroup.ADMIN, "roles/{id}", RequestType.GET),
    ADMIN_ROLE_CREATE(RouteGroup.ADMIN, "roles", RequestType.POST),
    ADMIN_ROLE_UPDATE(RouteGroup.ADMIN, "roles/{id}", RequestType.PUT),
    ADMIN_ROLE_DELETE(RouteGroup.ADMIN, "roles/{id}", RequestType.DELETE),
    ADMIN_ROLE_ASSIGN_PERMISSION(RouteGroup.ADMIN, "roles/{id}/permissions", RequestType.POST),
    ADMIN_ROLE_REMOVE_PERMISSION(RouteGroup.ADMIN, "roles/{id}/permissions/{permissionId}", RequestType.DELETE),

    // Permission Management
    ADMIN_PERMISSIONS_LIST(RouteGroup.ADMIN, "permissions", RequestType.GET),

    // Document viewing
    DOCUMENTS(RouteGroup.DOCS, "documents/{name}", RequestType.GET),

    // Audit & Activity
    // ADMIN_ACTIVITY_LOG(RouteGroup.ADMIN, "activity/log", RequestType.GET),
    // ADMIN_USER_ACTIVITY(RouteGroup.ADMIN, "activity/user/{userId}", RequestType.GET),
    // ADMIN_SYSTEM_STATS(RouteGroup.ADMIN, "stats", RequestType.GET),

    TEST_CAPTCHA_PAGE(RouteGroup.TESTING, "test-captcha-page", RequestType.GET, requiresAuth = false),
    TEST_GETCONFIRMATIONLINK(RouteGroup.TESTING, "confirmation-link", RequestType.GET, requiresAuth = false),
    TEST_PWRESETLINK(RouteGroup.TESTING, "pwreset-link", RequestType.GET, requiresAuth = false),
    TEST_ROUTES(RouteGroup.TESTING, "routes", RequestType.GET, requiresAuth = false),
    TEST_CONFIGURATION(RouteGroup.TESTING, "configuration", RequestType.GET, requiresAuth = false),
;

    /**
     * Returns the full path including the group's base route
     * Example: "api/calculate/getsolution/{solutionId}"
     */
    val fullPath: String
        get() = "${group.baseRoute}/$path"

    /**
     * Returns the effective permission requirement for this route.
     * If permissionOverride is set, use that; otherwise use the group's default.
     */
    val requirement: PermissionRequirement
        get() = permissionOverride ?: group.requirement

    /**
     * Helper to check if this route has a permission override
     */
    val hasPermissionOverride: Boolean
        get() = permissionOverride != null


}