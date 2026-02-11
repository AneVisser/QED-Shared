package qed.testbaseclass.sut.dairymax_REST.dto

/**
 * This file should actually be part of main/kotlin/dto of DairyMax_V2_UI, but
 * as long as the Delphi Json builder exists, this needs to be here
 * Todo: move back to where it belongs after json builder for Delphi has been removed
 */

import kotlinx.serialization.Serializable

// Data classes
@Serializable
data class NutrientRow(
    val nutid: Int,
    val nutcode: String,
    val nutdescrip: String,
    val nutunitnum: Int,
    val visible: Boolean? = null,
    val nutscript: String? = null,
    val ord: Int? = null,
    val editable: Boolean? = null
)

