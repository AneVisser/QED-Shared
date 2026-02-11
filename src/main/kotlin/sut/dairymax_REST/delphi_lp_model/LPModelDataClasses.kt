package qed.sut.dairymax_REST

import kotlinx.serialization.Serializable


@Serializable
data class LPParams(
    val numperiods: Int,
    val numanimals: Int
)
@Serializable
data class OpenSolverResponse(
    val id: String,
    val function: String,
    val status: Int
)
@Serializable
data class CloseSolverResponse(
    val id: String,
    val function: String,
    val action: String
)
@Serializable
data class LPMBParams(
    val numperiods: Int,
    val numsubsolvers: Int
)
@Serializable
data class DefaultNutrientParams(
    val nutnumber: Int,
    val nutdefunit: Int,
    val nutcode: String,
    val nutdescrip: String,
    val equation: String,
    val visible: Boolean,
)
@Serializable
data class Ingredient(
    val owner: String,
    val code: String,
    val ingtype: Int,
    val ingid: Int,
    val price: Double,
    val dm: Double,
    val edm: Double
)
@Serializable
data class IngNutValue(
    val ingid: Int,
    val nutnumber: Int,
    val nutvalue: Double,
    val script: String
)
@Serializable
data class SubSolver(
    val subsolver: Int,
)
@Serializable
data class LPIngredientAmountConstraint(
    val ingid: Int,
    val wastageoption: Int,
    val boundunit: Int,
    val grpcontr: Int
)
@Serializable
data class LPNutrientAmountConstraint(
    val nutnumber: Int,
    val boundunit: Int,
    val groupcontr: Int
)
@Serializable
data class LPGroupAmountConstraint(
    val grpid: Int,
    val boundunit: Int
)

@Serializable
data class LPNutrientRatioConstraint(
    val nutnum: Int,
    val nutdenom: Int
)
@Serializable
data class NutrientRatioBounds(
    val constnum: Int,
    val unitnum: Int,
    val unitdenom: Int,
    val period: Int,
    val minimum: Double,
    val maximum: Double,
)

@Serializable
data class LPGroupRatioConstraint(
    val grpnum: Int,
    val grpdenom: Int
)

@Serializable
data class LPMaxProfitConstraint(
    val nutnumber: Int,
    val boundunit: Int
)

@Serializable
data class LPSubstitutionConstraint(
    val substrate: Double,
    val maxdmi: Double,
    val minroughage: Double
)

@Serializable
data class LPFeedBudgetConstraint(
    val ingid: Int,
    val silageid: Int,
    val area: Double,
    val areaunit: Int,
    val constunit: Int
)
@Serializable
data class LPFeedBunkerConstraint(
    val ingid: Int,
    val tonnesafstored: Double,
    val startperiod: Int,
    val lastperiod: Int,
    val constunit: Int,
    val replace: Boolean,
    val owncrop: Boolean,
    val startgrowing: Int,
    val endgrowing: Int,
    val yieldperha: Int,
    val costperha: Int,
    val canpurchase: Boolean
)
@Serializable
data class LPNutrientDisposalConstraint(
    val nutnumber: Int,
    val disposalcost: Double,
    val boundunit: Int
)
@Serializable
data class LPWeightChangeConstraint(
    val energynut: Int,
    val efficiencywttoenergy: Double,
    val efficiencyenergytowt: Double,
    val wtenergycontent: Double,
    val constunit: Int
)
@Serializable
data class IngredientId(
    val ingid : Int
)
@Serializable
data class ConstraintNum(
    val constnum : Int
)
@Serializable
data class ConstraintUnitList(
    val constnum : Int,
    val consttype : Int
)
@Serializable
data class IngredientAmountBound(
    val constnr : Int,
    val period : Int,
    val minimum : Double,
    val maximum : Double,
    val wastage : Double,
)
@Serializable
data class NutrientAmountBound(
    val constnr : Int,
    val period : Int,
    val minimum : Double,
    val maximum : Double
)
@Serializable
data class NutrientInput(
    val nutcode: String,
    val dbValue: Double,
    val script: String,
    val nutUnitNum: Int,
    val nutId: Int,
    val conversionFactor: Int = 1
)

@Serializable
data class StreamFileData(
    val dumpdir : String,
    var filename : String
)

@Serializable
data class StreamFile(
    val filename : String,
    val objectiveValue : Double
)

@Serializable
data class StartOfBudget (
    val startofbudget : Int
)
@Serializable
data class StreamResult (
    val id : String,
    val function : String,
    val objectiveValue:Double
)
@Serializable
data class SummaryQuery (
    val breed : String,
    val milkfat : Double,
    val milkprot : Double,
    val milkprice : Double,
    val maturecowweight : Int
)
