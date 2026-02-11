package qed.testbaseclass.sut.dairymax_REST.lp_model

import SummaryResponse
import kotlinx.serialization.Serializable
import qed.testbaseclass.sut.dairymax_REST.delphi_lp_model.EconomicsReport
import qed.testbaseclass.sut.dairymax_REST.delphi_lp_model.NutrientReport

@Serializable
data class LPModelFullResponse (
        val ingredientSolution: IngredientSolution,
        val summary: SummaryResponse,
        val summaryChartData: SummaryChartData,
        val economicsReport: EconomicsReport? = null,
        val nutrientReport: NutrientReport? = null
    )
