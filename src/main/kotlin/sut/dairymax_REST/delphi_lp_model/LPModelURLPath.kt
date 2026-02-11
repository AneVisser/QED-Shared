package qed.sut.dairymax_REST

import LPIngredientAmountConstraintList
import LPSolution
import SummaryResponse
import qed.testbaseclass.IURLPath
import qed.testbaseclass.PayloadKind
import qed.testbaseclass.PayloadKind.Single
import qed.testbaseclass.RequestType
import qed.testbaseclass.sut.dairymax_REST.lp_model.IngredientSolution
import qed.testbaseclass.sut.dairymax_REST.lp_model.SummaryChartData
import qed.testbaseclass.sut.dairymax_REST.lp_model.TreeTestResults


enum class LPmodelURLPath(private val path: String,
                          override val method: RequestType,
                          override val responseKind: PayloadKind?,
                          override val payloadKind: PayloadKind?
) : IURLPath {
    SLOW("/slow", RequestType.GET, null, null),
    SANITYCHECK("/sanitycheck", RequestType.GET, null, null),
    LPSOLVEVERSION("/lpsolveversion/{id}",RequestType.GET , null, null),
    OPENSOLVER("/opensolver", RequestType.POST , null, Single(LPParams::class)),
    CLOSESOLVER("/closesolver/{id}", RequestType.GET , null, null),
    OPENMULTIBLENDSOLVER("/openmultiblendsolver", RequestType.POST , Single(OpenSolverResponse::class), Single(LPMBParams::class)),
    CLOSEMULTIBLENDSOLVER("/closemultiblendsolver/{mbid}", RequestType.GET , Single(CloseSolverResponse::class), null),
    ADDDEFAULTNUTRIENT("/adddefaultnutrient/{mbid}", RequestType.POST , null, Single(DefaultNutrientParams::class)),
    ADDINGREDIENT("/addingredient/{mbid}", RequestType.POST , null, Single(Ingredient::class)),
    ADDINGNUTVALUE("/addingnutvalue/{mbid}", RequestType.POST , null, Single(IngNutValue::class)),
    CALCULATENUTRIENTVALUES("/calculatenutrientvalues/{mbid}", RequestType.GET , null, null),
    FORMULATE("/formulate", RequestType.POST , null, null), //todo (not used in php, but might for singleblend
    // should become obsolete, as polymorphism should take care of whether it is a single or multiblend solver
    MULTIBLENDFORMULATE("/multiblendformulate/{mbid}", RequestType.GET , Single(StreamResult::class), null),  //todo
    FORMULATEFROMSTREAM("/formulatefromstream/{mbid}", RequestType.POST , null, Single(StreamFileData::class)),  // temporary, for testing
    SETCURRENTSUBSOLVER("/setcurrentsubsolver/{mbid}", RequestType.POST , null, Single(SubSolver::class)),
    ADDINGREDIENTAMOUNTCONSTRAINT("/addingredientamountconstraint/{mbid}", RequestType.POST , Single(LPIngredientAmountConstraintList::class), Single(LPIngredientAmountConstraint::class)),
    ADDNUTRIENTAMOUNTCONSTRAINT("/addnutrientamountconstraint/{mbid}", RequestType.POST , null, Single(LPNutrientAmountConstraint::class)),
    ADDGROUPAMOUNTCONSTRAINT("/addgroupamountconstraint/{mbid}", RequestType.POST , null, Single(LPGroupAmountConstraint::class)),
    ADDNUTRIENTRATIOCONSTRAINT("/addnutrientratioconstraint/{mbid}", RequestType.POST , null, Single(LPNutrientRatioConstraint::class)),
    ADDGROUPRATIOCONSTRAINT("/addgroupratioconstraint/{mbid}", RequestType.POST , null, Single(LPGroupRatioConstraint::class)),
    ADDMAXPROFITCONSTRAINT("/addmaxprofitconstraint/{mbid}", RequestType.POST , null, Single(LPMaxProfitConstraint::class)),
    ADDSUBSTITUTIONCONSTRAINT("/addsubstitutionconstraint/{mbid}", RequestType.POST , null, Single(LPSubstitutionConstraint::class)),
    ADDFEEDBUDGETCONSTRAINT("/addfeedbudgetconstraint/{mbid}", RequestType.POST , null, Single(LPFeedBudgetConstraint::class)),
    ADDFEEDBUNKERCONSTRAINT("/addfeedbunkerconstraint/{mbid}", RequestType.POST , null, Single(LPFeedBunkerConstraint::class)),
    ADDNUTRIENTDISPOSALCONSTRAINT("/addnutrientdisposalconstraint/{mbid}", RequestType.POST , null, Single(LPNutrientDisposalConstraint::class)),
    ADDWEIGHTCHANGECONSTRAINT("/addweightchangeconstraint/{mbid}", RequestType.POST , null, Single(LPWeightChangeConstraint::class)),
    GETNUTRIENTDEFINITIONUNITS("/getnutrientdefinitionunits", RequestType.GET , null, null), //todo
    GETNUTRIENTDEFCONVERSIONFACTORS("/getnutrientdefconversionfactors", RequestType.GET , null, null), //
    GETDEFAULTNUTRIENTLIST("/getdefaultnutrientlist/{mbid}", RequestType.GET , null, null),
    GETINGREDIENTLIST("/getingredientlist/{mbid}", RequestType.GET , null, null),
    GETINGREDIENTAMOUNTCONSTRAINTLIST("/getingredientamountconstraintlist/{mbid}", RequestType.GET , null, null),
    GETNUTRIENTCOUNT("/getnutrientcount/{mbid}", RequestType.GET , null, null),
    GETNUTVALUEFROMSCRIPT("/getnutvaluefromscript/{mbid}", RequestType.POST , null, PayloadKind.ListOf(NutrientInput::class)),  // todo: function has not been implemented yet in Delphi
    GETINGREDIENTNUTRIENTLIST("/getingredientnutrientlist/{mbid}", RequestType.POST , null, Single(IngredientId::class)),
    GETINGREDIENTGROUPLIST("/getingredientgrouplist/{mbid}", RequestType.POST , null, Single(IngredientId::class)),
    GETINGREDIENTTYPE("/getingredienttype/{mbid}", RequestType.POST , null, Single(IngredientId::class)),
    GETINGAMNTCONSTNUTRIENTS("/getingamntconstnutrients/{mbid}", RequestType.POST , null, Single(ConstraintNum::class)),
    GETNUMPERIODS("/getnumperiods/{mbid}", RequestType.GET , null, null),
    GETNUTRIENTAMOUNTCONSTRAINTLIST("/getnutrientamountconstraintlist/{mbid}", RequestType.GET , null, null),
    GETINGAMNTSOLUTION("/getingamntsolution", RequestType.GET , null, null), //todo
    GETNUTAMNTSOLUTION("/getnutamntsolution", RequestType.GET , null, null), //todo
    GETCONSTRAINTUNITLIST("/getconstraintunitlist/{mbid}", RequestType.POST , null, Single(ConstraintUnitList::class)),
    ADDINGREDIENTAMOUNTBOUND("/addingredientamountbound/{mbid}", RequestType.POST , null, Single(IngredientAmountBound::class)),

    ADDNUTRIENTAMOUNTBOUND("/addnutrientamountbound/{mbid}", RequestType.POST , null, Single(NutrientAmountBound::class)),
    GETINGAMNTBOUNDVALUES("/getingamntboundvalues/{mbid}", RequestType.POST , null, Single(ConstraintNum::class)),
    INGAMNTCONSTRESET("/ingamntconstreset", RequestType.GET , null, null),
    SETINGREDIENTAMOUNTGROUPCONTR("/setingredientamountgroupcontr", RequestType.POST , null, null),
    GETNUTAMNTBOUNDVALUES("/getnutamntboundvalues/{mbid}", RequestType.POST , null, Single(ConstraintNum::class)),  // todo
    GETOBJECTIVEVALUE("/getobjectivevalue", RequestType.GET , null, null), //todo
    NUTAMNTCONSTRESET("/nutamntconstreset", RequestType.GET , null, null),
    GETNUTSOLUTION("/getnutsolution", RequestType.GET , null, null), //todo
    GETTOTALFORMULATED("/gettotalformulated", RequestType.GET , null, null), //todo
    GETINGSOLUTION("/getingsolution", RequestType.GET , null, null), //todo
    GETTOTALCOST("/gettotalcost", RequestType.GET , null, null), //todo
    ADDNUTRIENTRATIOBOUND("/addnutrientratiobound/{mbid}", RequestType.POST , null, Single(NutrientRatioBounds::class)),
    GETNUTRIENTRATIOCONSTRAINTLIST("/getnutrientratioconstraintlist", RequestType.GET , null, null), //todo
    GETNUTRATBOUNDVALUES("/getnutratboundvalues", RequestType.GET , null, null), //todo
    GETNUTRATSOLUTION("/getnutratsolution", RequestType.GET , null, null), //todo
    ADDGROUPAMOUNTBOUND("/addgroupamountbound", RequestType.GET , null, null),
    ADDINGREDIENTTOGROUP("/addingredienttogroup", RequestType.GET , null, null), //todo
    GETGROUPAMOUNTCONSTRAINTLIST("/getgroupamountconstraintlist", RequestType.GET , null, null), //todo
    GETGRPAMNTSOLUTION("/getgrpamntsolution", RequestType.GET , null, null), //todo
    ADDGROUPRATIOBOUND("/addgroupratiobound", RequestType.GET , null, null),
    GETGROUPRATIOCONSTRAINTLIST("/getgroupratioconstraintlist", RequestType.GET , null, null), //todo
    GETGRPRATBOUNDVALUES("/getgrpratboundvalues", RequestType.GET , null, null), //todo
    GETGRPRATSOLUTION("/getgrpratsolution", RequestType.GET , null, null), //todo
    ADDMAXPROFITBOUND("/addmaxprofitbound", RequestType.GET , null, null), //todo
    SETMAXPROFITNUMPRODPORTIONS("/setmaxprofitnumprodportions", RequestType.POST , null, null), //todo
    SETMAXPROFITPRODVALUE("/setmaxprofitprodvalue", RequestType.POST , null, null), //todo
    SETMAXPROFITPRODPORTIONSIZE("/setmaxprofitprodportionsize", RequestType.POST , null, null), //todo
    SETMAXPROFITNUTREQPERPRODUNIT("/setmaxprofitnutreqperprodunit", RequestType.POST , null, null), //todo
    GETFORMULATIONRESULT("/getformulationresult", RequestType.GET , null, null), //todo
    GETMAXPROFITCONSTRAINTLIST("/getmaxprofitconstraintlist", RequestType.GET , null, null), //todo
    GETMAXPROFITSOLUTION("/getmaxprofitsolution", RequestType.GET , null, null), //todo
    GETMAXPROFITPRODUCTION("/getmaxprofitproduction", RequestType.GET , null, null), //todo
    MAXPROFITSETMINTOTALPROD("/maxprofitsetmintotalprod", RequestType.POST , null, null), //todo
    GRPAMNTCONSTRESET("/grpamntconstreset", RequestType.POST , null, null),
    SETINGREDIENTPRICE("/setingredientprice", RequestType.POST , null, null),
    GETSUBSTITUTIONCONSTRAINTLIST("/getsubstitutionconstraintlist", RequestType.GET , null, null),//todo
    INGCONSTNUTVALUEOVERRIDE("/ingconstnutvalueoverride", RequestType.POST , null, null),
    SETFEEDBUDGETNUMANIMALS("/setfeedbudgetnumanimals", RequestType.POST   , null, null),
    GETFEEDBUDGETCONSTRAINTLIST("/getfeedbudgetconstraintlist", RequestType.GET , null, null), //todo
    SETFEEDBUDGETDMPRODPERDAY("/setfeedbudgetdmprodperday", RequestType.POST , null, null),//todo
    GETFEEDBUDGETPASTUREDATA("/getfeedbudgetpasturedata", RequestType.GET , null, null),//todo
    SETFEEDBUDGETCOSTPERAREAUNIT("/setfeedbudgetcostperareaunit", RequestType.POST , null, null), //todo
    SETFEEDBUDGETCONSERVATIONCOSTPERTONNE("/setfeedbudgetconservationcostpertonne", RequestType.POST , null, null),//todo
    SETFEEDBUDGETWASTAGE("/setfeedbudgetwastage", RequestType.POST , null, null),//todo
    SETFEEDBUDGETSILAGEWASTAGE("/setfeedbudgetsilagewastage", RequestType.POST , null, null),//todo
    GETFEEDBUDGETSOLUTIONPASTURE("/getfeedbudgetsolutionpasture", RequestType.GET , null, null),//todo
    GETFEEDBUDGETSOLUTIONSILAGE("/getfeedbudgetsolutionsilage", RequestType.GET , null, null),//todo
    GETFEEDBUDBOUNDVALUES("/getfeedbudboundvalues", RequestType.GET , null, null),//todo
    GETFEEDBUDGETCONSTNUTRIENTS("/getfeedbudgetconstnutrients", RequestType.GET , null, null),//todo
    FEEDBUDGETGETSILAGEFED("/feedbudgetgetsilagefed", RequestType.GET , null, null),//todo
    FEEDBUDGETGETSILAGEMADE("/feedbudgetgetsilagemade", RequestType.GET , null, null),//todo
    FEEDBUDGETGETSILAGEMADEDM("/feedbudgetgetsilagemadedm", RequestType.GET , null, null),//todo
    GETFEEDBUDGETCONSTSILAGENUTRIENTS("/getfeedbudgetconstsilagenutrients", RequestType.GET , null, null),//todo
    SETFEEDBUDGETSILAGELOSSFACTOR("/setfeedbudgetsilagelossfactor", RequestType.POST , null, null),
    SETNUTRIENTVALUEOVERRIDE("/setnutrientvalueoverride", RequestType.POST , null, null),//todo
    SETINGREDIENTNUTDBVALUE("/setingredientnutdbvalue", RequestType.POST , null, null),
    GETFEEDBUNKERCONSTRAINTLIST("/getfeedbunkerconstraintlist", RequestType.GET , null, null),//todo
    GETFEEDBUNKERRHS("/getfeedbunkerrhs", RequestType.GET , null, null),//todo
    GETFEEDBUNKERSOLUTION("/getfeedbunkersolution", RequestType.GET , null, null),//todo
    GETFEEDBUNKERCONSTRAINTNUTRIENTS("/getfeedbunkerconstraintnutrients", RequestType.GET , null, null),//todo
    SETFEEDBUNKERWASTAGE("/setfeedbunkerwastage", RequestType.POST , null, null),//todo
    SETFEEDBUNKERCONSTRAINTPRICEPERTONNE("/setfeedbunkerconstraintpricepertonne", RequestType.POST , null, null),//todo
    FEEDBUNKERADDBALANCENUTRIENT("/feedbunkeraddbalancenutrient", RequestType.POST , null, null),//todo
    SETFEEDBUNKERCONSTRAINTBALANCENEXTYEAR("/setfeedbunkerconstraintbalancenextyear", RequestType.POST , null, null),//todo
    SETFEEDBUNKERCONSTRAINTCANPURCHASE("/setfeedbunkerconstraintcanpurchase", RequestType.POST , null, null),//todo
    SETFEEDBUNKERFEEDBUDGETREC("/setfeedbunkerfeedbudgetrec", RequestType.POST , null, null),//todo
    CLEARINGREDIENTOVERRIDEHANDLERS("/clearingredientoverridehandlers", RequestType.POST , null, null),
    SETFEEDBUDSILAGENUTRIENTOVERRIDE("/setfeedbudsilagenutrientoverride", RequestType.POST , null, null),
    GETFEEDBUNKERTOHARVEST("/getfeedbunkertoharvest", RequestType.GET , null, null),
    GETFEEDBUNKERTOPURCHASE("/getfeedbunkertopurchase", RequestType.GET , null, null),
    GETWEIGHTCHANGE("/getweightchange", RequestType.GET , null, null),//todo
    ADDNUTRIENTDISPOSALBOUND("/addnutrientdisposalbound", RequestType.POST , null, null),
    GETNUTRIENTDISPOSALCONSTRAINTLIST("/getnutrientdisposalconstraintlist", RequestType.GET , null, null),//todo
    SETNUTRIENTDISPOSALCOST("/setnutrientdisposalcost", RequestType.POST , null, null),
    SETINGREDIENTAMOUNTWASTAGEOPTION("/setingredientamountwastageoption", RequestType.POST , null, null),
    SETINGREDIENTAMOUNTBOUNDUNIT("/setingredientamountboundunit", RequestType.POST , null, null),
    SETNUTRIENTAMOUNTBOUNDUNIT("/setnutrientamountboundunit", RequestType.POST , null, null),
    SETNUTRIENTAMOUNTGROUPCONTR("/setnutrientamountgroupcontr", RequestType.POST , null, null),
    SETMAXPROFITBOUNDUNIT("/setmaxprofitboundunit", RequestType.POST , null, null),
    ADDMULTIBLENDCONSTRAINT("/addmultiblendconstraint", RequestType.POST , null, null),
    ADDMULTIBLENDBOUND("/addmultiblendbound", RequestType.POST , null, null),
    ADDMULTIBLENDPERIOD("/addmultiblendperiod", RequestType.POST , null, null),
    GETMULTIBLENDCONSTRAINTLIST("/getmultiblendconstraintlist", RequestType.GET , null, null),//todo
    GETFORMULATIONRESULTSTRING("/getformulationresultstring", RequestType.GET , null, null),//todo)
    READFROMSTREAM("/readfromstream/{mbid}", RequestType.POST , Single(StreamResult::class), Single(StreamFileData::class)),
    WRITETOSTREAM("/writetostream/{mbid}", RequestType.POST , null, Single(StreamFileData::class)),
    READFROMJSON("/readfromjson/{mbid}", RequestType.POST , null, Single(StreamFileData::class)),
    WRITETOJSON("/writetojson/{mbid}", RequestType.POST , null, Single(StreamFileData::class)),
    GETJSON("/getjson/{mbid}", RequestType.GET, Single(LPSolution::class), null),
    SETREPORTDIRECTORY("/setreportdirectory", RequestType.POST , null, null),//todo
    SETREPORTINGVARIABLES("/setreportingvariables", RequestType.POST , null, null),
    SETSTARTOFBUDGET("/setstartofbudget/{mbid}", RequestType.POST , Single(StartOfBudget::class), Single(StartOfBudget::class)),
    SETSTARTOFLACTATION("/setstartoflactation", RequestType.POST , null, null),//todo
    GETLACTATIONPERIOD("/getlactationperiod", RequestType.GET , null, null),//todo
    SETCALVINGINTERVAL("/setcalvinginterval", RequestType.POST , null, null),
    SETNUMPRODUCTIONGROUPS("/setnumproductiongroups", RequestType.POST , null, null),
    SETNUMANIMALSPERPRODPARITYGROUP("/setnumanimalsperprodparitygroup", RequestType.POST , null, null),
    SETNUMANIMALS("/setnumanimals", RequestType.POST , null, null),//todo
    GETAGGREGATEDVALUES("/getaggregatedvalues", RequestType.GET , null, null),
    SETPARITYGROUP("/setparitygroup", RequestType.POST , null, null),//todo
    SETPRODUCTIONGROUP("/setproductiongroup", RequestType.POST , null, null),//todo
    GETINGCONSTUNITS("/getingconstunits", RequestType.GET , null, null),//todo
    GETNUMANIMALS("/getnumanimals", RequestType.GET , null, null),//todo
    FEEDBUDGETGETSILAGEFEDDM("/feedbudgetgetsilagefeddm", RequestType.GET , null, null),//todo
    GETSUMMARYDATA("/getsummarydata/{mbid}", RequestType.GET , null, null),//todo
    INGREDIENTEXISTS("/ingredientexists", RequestType.POST , null, null),//todo
    GETYEARFEED("/getyearfeed", RequestType.GET , null, null),//todo
    GETYEARCOST("/getyearcost", RequestType.GET , null, null),
    GETSOLUTION("/getsolution", RequestType.GET , null, null),//todo
    GETNUTRIENTSOLUTION("/getnutrientsolution", RequestType.GET , null, null),//todo
    GETSUMMARY("/getsummary/{mbid}", RequestType.POST , Single(SummaryResponse::class), Single(SummaryQuery::class)),
    GETINGREDIENTSOLUTION("/getingredientsolution/{mbid}", RequestType.GET , Single(IngredientSolution::class), null),//todo
    ADDPRODUCTIONGROUP("/addproductiongroup", RequestType.POST , null, null),//todo
    ADDYOUNGSTOCKGROUP("/addyoungstockgroup", RequestType.POST , null, null),//todo
    SETLACTATIONLENGTH("/setlactationlength", RequestType.POST , null, null),//todo
    GETSUMMARYCHARTDATA("/getsummarychartdata/{mbid}", RequestType.GET , Single(SummaryChartData::class), null),
    GETMONTHCHARTDATA("/getmonthchartdata/{mbid}", RequestType.GET , null, null),//todo
    SETMILKPRICECONVERSIONFACTOR("/setmilkpriceconversionfactor", RequestType.POST , null, null),//todo
    ADDREPORTOPTION("/addreportoption", RequestType.POST , null, null),//todo
    SETSILAGELOSSFACTOR("/setsilagelossfactor", RequestType.POST , null, null),//todo
    CLEARINGREDIENTAMOUNTCONSTRAINTS("/clearingredientamountconstraints", RequestType.POST , null, null),//todo
    OPENMULTIBLENDSOLVERFROMSTREAM("/openmultiblendsolverfromstream", RequestType.POST , null, null),//todo
    SETFARMGROUPDEFINITION("/setfarmgroupdefinition", RequestType.POST , null, null),//todo
    ADDGRAZEDCROPCONSTRAINT("/addgrazedcropconstraint", RequestType.POST , null, null),//todo
    SETFEEDBUDGETCONSTRAINTFORGRAZEDCROPCONSTRAINT("/setfeedbudgetconstraintforgrazedcropconstraint", RequestType.POST , null, null),//todo
    ADDPRODGROUPINGREDIENTCONSTRAINT("/addprodgroupingredientconstraint", RequestType.POST , null, null),//todo
    ADDPRODGROUPINGREDIENTBOUND("/addprodgroupingredientbound", RequestType.POST , null, null),//todo
    SETMILKCONTENTS("/setmilkcontents", RequestType.POST , null, null),//todo
    ADDSTOREDCROPCONSTRAINT("/addstoredcropconstraint", RequestType.POST , null, null),//todo
    SETFEEDBUDGETCONSTRAINTFORSTOREDCROPCONSTRAINT("setfeedbudgetconstraintforstoredcropconstraint", RequestType.POST , null, null),

    // the URL's below are only for internal API testing, and therefore don't have a defined responseKind and payloadKind, and should be called by sendUntyped
    REST_RUNALL("/test/run-all/{mbid}", RequestType.GET , Single(TreeTestResults::class), null),

    ;

    override val route: String
        get() = this.path


}


