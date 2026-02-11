import kotlinx.serialization.Serializable
import qed.sut.dairymax_REST.LPFeedBunkerConstraint
import qed.testbaseclass.Matrix
import qed.testbaseclass.Vector
import qed.testbaseclass.sut.dairymax_REST.delphi_lp_model.LPDelphiIngredient
import qed.testbaseclass.sut.dairymax_REST.dto.NutrientRow

// Wrapper for the API response
@Serializable
data class LPSolution(
    val id: String,
    val function: String,
    val lpmodel: LPModel
)

@Serializable
data class LPModel(
    val solver: LPFeedFormulationData
)

@Serializable
data class LPDualSolution (
    val boundvalue_min : Double?,
    val boundvalue_max : Double?,
    val primalSolution_min : Double?,
    val primalSolution_max : Double?,
    val dual_min : Double?,
    val dual_max : Double?,
    val islimiting_min : Boolean?,
    val islimiting_max : Boolean?,
    val dualfrom_min : Double?,
    val dualfrom_max : Double?,
    val dualtill_min : Double?,
    val dualtill_max : Double?,
    val rownr_min : Int,
    val rownr_max : Int,
)

// The actual data structure (what was previously the root)
@Serializable
data class LPFeedFormulationData(
    val defaultnutrientlist: List<NutrientRow>,
    val ingredientlist: LPIngredientList,
    val prodgroupingconstraintlist: LPProdGroupingConstraintList,
    val delphisolver: MultiblendSolver,
)

@Serializable
data class IngredientNutrientValues(
    val nutrientItems: List<IngredientNutrientValue> = emptyList()
){
    fun getValue(nutid: Int): Double? = nutrientItems.find { it.nutid == nutid }?.nutvalue
}



@Serializable
data class IngredientNutrientValue(
    val nutid: Int,
    val nutvalue: Double?,
)

@Serializable
data class LPIngredientList(
    val items: List<LPDelphiIngredient> = emptyList()
)

@Serializable
data class LPProdGroupingConstraintList(
    val items: List<String>      // Appears to be empty in your data
)

@Serializable
data class MultiblendSolver(
    val lcsolverlist: List<LcSolver> = emptyList(),
    val multiblendconstraintlist: LPMultiBlendConstraintList,
    val farmgroups: LPFarmGroups,
    val youngstockgroups: LPYoungStockGroups,
    val lactationlength: Int,
    val milkpriceconversionfactor: Double,
    val silagelossfactor: Double,
    val objectivevalue: Double,
    val farmgroupdefinition: Int
)

@Serializable
data class LcSolver(
    val numperiods: Int,
    val numanimals: Int,
    val startoflactation: Int,
    val lactationlength: Int,
    val startofbudget: Int,
    val productiongroup: Int,
    val paritygroup: Int,
    val milkpriceconversionfactor: Double,
    val milkfatperc: Double,
    val milkprotperc: Double,
    val ingredientamountconstraintlist: LPIngredientAmountConstraintList,
    val nutrientamountconstraintlist: LPNutrientAmountConstraintList,
    val nutrientratioconstraintlist: LPNutrientRatioConstraintList,
    val groupamountconstraintlist: LPGroupAmountConstraintList,
    val groupratioconstraintlist: LPGroupRatioConstraintList,
    val maxprofitconstraintlist: LPMaxProfitConstraintList,
    val sustitutionconstraintlist: LPSubstitutionConstraintList,
    val feedbudgetconstraintlist: LPFeedBudgetConstraintList,
    val feedbunkerconstraintlist: LPFeedBunkerConstraintList,
    val grazedcropconstraintlist: LPGrazedCropConstraintList,
    val storedcropconstraintlist: LPStoredCropConstraintList,
    val weightchangeconstraintlist: LPWeightChangeConstraintList,
    val nutrientdisposalconstraintlist: LPNutrientDisposalConstraintList,
)

@Serializable
data class LPIngredientAmountConstraintList(
    val items: List<LPIngredientAmountConstraint> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPIngredientAmountConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double?>,
    val maxbounds: Vector<Double?>,
    val dualsolutions : List<LPDualSolution>,
    val groupcontr: Int,
    val wastage: Vector<Double>,
    val nutrientoverride : Matrix<Double>,
    val wastageoption : String,
    val ingredientref : Int,
    val formulatedintakedm : Vector<Double>,
    val formulatedusagedm : Vector<Double>,
)


@Serializable
data class LPNutrientAmountConstraintList(
    val items: List<LPNutrientAmountConstraint> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPNutrientAmountConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val nutrientref: Int,
    val groupcontr: Int
)

@Serializable
data class LPMaxProfitConstraintList(
    val items: List<LPMaxProfitConstraint> = emptyList(),
    val numanimals: Int,
    val prodvalue: Vector<Double>,
    val prodportions: Matrix<Double>,
    val numprodportions: Int,
    val mintotalproduction: Double
)

@Serializable
data class LPMaxProfitConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double?>,
    val maxbounds: Vector<Double?>,
    val dualsolutions : List<LPDualSolution>,
    val nutrientref: Int,
    val groupcontr: Int,
    val nutreqperprodunit: Matrix<Double>,
    val maintenance: Vector<Double>
)

@Serializable
data class LPFeedBudgetConstraintList(
    val items: List<LPFeedBudgetConstraint> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPFeedBudgetConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double?>,
    val maxbounds: Vector<Double?>,
    val dualsolutions : List<LPDualSolution>,
    val groupcontr: Int,
    val wastage: Vector<Double>,
    val nutrientoverride: Matrix<Double>,
    val wastageoption: String,
    val ingredientref: Int,
    val area: Double,
    val areaunitnum: Int,
    val prodperhaperday: Vector<Double>,
    val costperareaunit: Vector<Double>,
    val conservationcostpertonne: Vector<Double>,
    val silagewastage: Vector<Double>,
    val silageref: Int,
    val silagelossfactor: Double,
    val formulatedpastureintakedm: Vector<Double>,
    val formulatedpastureusagedm: Vector<Double>,
    val formulatedsilageintakedm: Vector<Double>,
    val formulatedsilageusagedm: Vector<Double>,
    val formulatedsilagmadedm: Vector<Double>
)

@Serializable
data class LPFeedBunkerConstraintList(
    val items: List<LPFeedBunkerConstraint> = emptyList(),
    val numanimals: Int,
    val balancenutrients: Vector<Int>
)

@Serializable
data class LPGrazedCropConstraintList(
    val items: List<LPGrazedCropConstraint> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPGrazedCropConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double?>,
    val maxbounds: Vector<Double?>,
    val dualsolutions : List<LPDualSolution>,
    val groupcontr: Int,
    val wastage: Vector<Double>,
    val nutrientoverride: Matrix<Double>,
    val wastageoption: String,
    val ingredientref: Int,
    val formulatedintakedm: Vector<Double>,
    val formulatedusagedm: Vector<Double>,
    val costperha: Int,
    val numofha: Double,            // todo: should be int, change when delphi model is decommissioned
    val yield: Int,
    val startuse: Int,
    val enduse: Int,
    val startintake: Int,
    val endintake: Int,
    val feedbudgetindex: Int
)

@Serializable
data class LPStoredCropConstraintList(
    val items: List<LPStoredCropConstraint> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPStoredCropConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double?>,
    val maxbounds: Vector<Double?>,
    val dualsolutions : List<LPDualSolution>,
    val groupcontr: Int,
    val wastage: Vector<Double>,
    val nutrientoverride: Matrix<Double>,
    val wastageoption: String,
    val ingredientref: Int,
    val formulatedintakedm: Vector<Double>,
    val formulatedusagedm: Vector<Double>,
    val costperha: Int,
    val numofha: Double,
    val yield: Int,
    val startuse: Int,
    val enduse: Int,
    val startintake: Int,
    val endintake: Int,
    val feedbudgetindex: Int,
    val replace : Boolean,
    val owncrop : Boolean,
    val purchase: Boolean,
    val purchaseprice: Int,
    val tonnesafstored: Int
)

@Serializable
data class LPWeightChangeConstraintList(
    val items: List<LPWeightChangeConstraint> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPWeightChangeConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val energynutrient : Int,
    val weighttoenergyefficiency : Double?,
    val energytoweightefficiency : Double?,
    val weightenergycontent : Double?,
)

@Serializable
data class LPNutrientDisposalConstraintList(
    val items: List<LPNutrientDisposalConstraint> = emptyList(),
    val numanimals: Int
)
@Serializable
data class LPNutrientDisposalConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val nutrientref: Int,
    val groupcontr: Int,
    val retention : Vector<Double>,
    val allowance : Vector<Double>,
    val disposalcost: Double?
)

@Serializable
data class LPNutrientRatioConstraintList(
    val items: List<LPNutrientRatioConstraint> = emptyList(),
    val numanimals: Int
)
@Serializable
data class LPNutrientRatioConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val nutrientref: Int,
    val groupcontr: Int,
    val nutrientdenomref: Int,
    val denomconstraintunitnum: Int
)

@Serializable
data class LPGroupAmountConstraintList(
    val items: List<LPGroupAmountConstraint> = emptyList(),
    val numanimals: Int
)
@Serializable
data class LPGroupAmountConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val group: Int,
)

@Serializable
data class LPGroupRatioConstraintList(
    val items: List<LPGroupRatioConstraint> = emptyList(),
    val numanimals: Int
)
@Serializable
data class LPGroupRatioConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val group: Int,
    val groupdenom : Int
)

@Serializable
data class LPSubstitutionConstraintList(
    val items: List<LPSubstitutionConstraint> = emptyList(),
    val numanimals: Int
)
@Serializable
data class LPSubstitutionConstraint(
    val numperiods: Int,
    val active: Boolean,
    val constraintunitnum: Int,
    val minbounds: Vector<Double>,
    val maxbounds: Vector<Double>,
    val dualsolutions : List<LPDualSolution>,
    val maxdmi: Double,
    val substitutionrate: Double,
    val minroughagefraction: Double,
)

@Serializable
data class LPMultiBlendConstraintList(
    val items: List<String> = emptyList(),
    val numanimals: Int
)

@Serializable
data class LPFarmGroups(
    val items: List<LPFarmGroup> = emptyList()
)

@Serializable
data class LPFarmGroup(
    val firstmonth: Int,
    val lastmonth: Int
)

@Serializable
data class LPYoungStockGroups(
    val items: List<LPYoungStockGroup> = emptyList()
)

@Serializable
data class LPYoungStockGroup(
    val firstmonth: Int,
    val lastmonth: Int
)

//// Helper classes for array and matrix data
//@Serializable
//data class Vector<T>(
//    val Length: Int,
//    val Data: List<T?>
//)
//
//@Serializable
//data class Matrix(
//    val Rows: Int,
//    val Cols: Int,
//    val Data: List<List<Double?>>
//)
