package qed.testbaseclass.sut.dairymax_REST.lp_model

import kotlinx.serialization.Serializable
// todo:Add YoungstockGroup data
@Serializable
data class IngredientSolution(
    val id: String,
    val function: String,
    val months: List<MonthData>, // array instead of dynamic keys
    val lactationlength: Int,
    val openingBCS : Double,
    val closingBCS : Double,
    val ElapsedTimeFormulation: String,
    val ElapsedTimeReporting: String,
    val ElapsedTimeTotal: String
)


@Serializable
data class MonthData(
    val monthName: String, // "Jan", "Feb", ...
    val numanimals: Int,
    val grouplabel: String,
    val lactationmonth: Int,
    val pasture: List<FeedItem> = emptyList(),
    val silage: List<FeedItem> = emptyList(),
    val feedbunkers: List<FeedItem> = emptyList(),
    val supplements: List<FeedItem> = emptyList(),
    val minerals: List<FeedItem> = emptyList(),
    val totalingredients: TotalIngredients,
    val economic: Economic,
    val nutrients: List<Nutrient> = emptyList(),
    val conservation: List<Conservation> = emptyList(),
    val farmgroups: List<FarmGroup> = emptyList(),
//    val youngstockgroups: List<YoungStockGroup> = emptyList()
)

@Serializable
data class FeedItem(
    val orig: String,
    val code: String,
    val inggroups: String,
    val status: String,
    val dmusage: Double?,
    val afusage: Double?,
    val dmintake: Double?,
    val afintake: Double?,
    val dmusagegrp: Double?,
    val afusagegrp: Double?,
    val cost: Double?,
    val objectivefrom: Double?,
    val currentprice: Double?,
    val objectivetill: Double?,
    val reducedcost: Double?
)

@Serializable
data class TotalIngredients(
    val DMUsage: Double?,
    val DMIntake: Double?,
    val AFUsage: Double?,
    val AFIntake: Double?,
    val DMUsageGrp: Double?,
    val AFUsageGrp: Double?,
    val Cost: Double?
)

@Serializable
data class Economic(
    val production: Double?,
    val milksolids: Double?,
    val weightchange: Double?,
    val bcsChangeMnth: Double?,
    var expectedBCS : Double?,
    var cumulativeWtChange : Double?,
    val feedcost: Double?,
    val profit: Double?,
    val grpproduction: Double?,
    val grpmilksolids: Double?,
    val grpfeedcost: Double?,
    val grpprofit: Double?,
    val milkprice: Double?,
    val dmIntake: Double?,
    val lowerprodprice: Double?,
    val upperprodprice: Double?,
    val milkpriceMS: Double?,
    val lowerprodpriceMS: Double?,
    val upperprodpriceMS: Double?
)

@Serializable
data class Nutrient(
    val nutcode: String,
    val usage: Double?,
    val intake: Double?,
    val min: Double?,
    val max: Double?,
    val formulatedvalue: Double?,
    val percofminconst: Double?,
    val percofmaxconst: Double?,
    val unit: String
)

@Serializable
data class Conservation(
    val orig: String,
    val code: String,
    val dmusagegrp: String,
    val afusagegrp: String,
    val hectares: String,
    val currentprice: String,
    val cost: String
)

@Serializable
data class FarmGroup(
    val numanimals: Int,
    val grouplabel: String,
    val lactationmonth: Int,
    val pasture: List<FeedItem> = emptyList(),
    val silage: List<FeedItem> = emptyList(),
    val feedbunkers: List<FeedItem> = emptyList(),
    val supplements: List<FeedItem> = emptyList(),
    val minerals: List<FeedItem> = emptyList(),
    val totalingredients: TotalIngredients,
    val economic: Economic,
    val nutrients: List<Nutrient> = emptyList(),
    val firstmonth: Int? = null,
    val lastmonth: Int? = null,
    val lactationgroups: List<LactationGroup> = emptyList(),
    val paritygroups: List<ParityGroup> = emptyList(),
    val isYoungstock: Boolean = false,
    val showMilkMetrics: Boolean = true,  // false for youngstock or dry periods
)

//@Serializable
//data class YoungStockGroup(
//    // define fields when needed
//)

@Serializable
data class LactationGroup(
    val numanimals: Int,
    val lactationgroup: Int,
    val lactationmonth: Int,
    val grouplabel: String,
    val pasture: List<FeedItem> = emptyList(),
    val silage: List<FeedItem> = emptyList(),
    val feedbunkers: List<FeedItem> = emptyList(),
    val supplements: List<FeedItem> = emptyList(),
    val minerals: List<FeedItem> = emptyList(),
    val totalingredients: TotalIngredients,
    val economic: Economic,
    val nutrients: List<Nutrient> = emptyList()
)

@Serializable
data class ParityGroup(
    val numanimals: Int,
    val lactationgroup: Int,
    val paritygroup: Int,
    val grouplabel: String,
    val lactationmonth: Int,
    val pasture: List<FeedItem> = emptyList(),
    val silage: List<FeedItem> = emptyList(),
    val feedbunkers: List<FeedItem> = emptyList(),
    val supplements: List<FeedItem> = emptyList(),
    val minerals: List<FeedItem> = emptyList(),
    val totalingredients: TotalIngredients,
    val economic: Economic,
    val nutrients: List<Nutrient> = emptyList()
)
