package qed.testbaseclass.sut.dairymax_REST.delphi_lp_model

import IngredientNutrientValues
import kotlinx.serialization.Serializable


enum class DelphiIngredientType() {
    PASTURE,
    SILAGE,
    FEEDBUNKER,
    GRAZEDCROP,
    STOREDCROP,
    SUPPLEMENT,
    MINERAL,
    GENERAL
}

@Serializable
data class LPDelphiIngredient(
    val ingid: Int,
    val ingcode: String,
    val ingdescrip: String,
    val price: Double,
    val dm: Double,
    val edm: Double,
    val ingredienttype: DelphiIngredientType,
    val groups : List<Int>,
    val nutrientValues: IngredientNutrientValues,
    val area: Int? = null,
    val yield: Int? = null,
    val costperha: Int? = null,
    val sowingmonth: Int? = null,
    val startfeeding: Int? = null,
    val endfeeding: Int? = null
)