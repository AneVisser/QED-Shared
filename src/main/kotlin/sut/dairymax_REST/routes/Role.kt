package qed.testbaseclass.sut.dairymax_REST.routes

enum class Role(
    var id: Int? = null,
    var description: String? = null
) {
    USER,
    MANAGER,
    ADMIN
}