package qed.sut.dairymax_REST

import qed.testbaseclass.IURLPath
import qed.testbaseclass.PayloadKind
import qed.testbaseclass.PayloadKind.*
import qed.testbaseclass.RequestType
import qed.testbaseclass.sut.dairymax_REST.cow_model.BreedInfo
import qed.testbaseclass.sut.dairymax_REST.cow_model.ContourInfo
import qed.testbaseclass.sut.dairymax_REST.cow_model.CowModelResponse
import qed.testbaseclass.sut.dairymax_REST.cow_model.DelphiLinProgParametersResponse
import qed.testbaseclass.sut.dairymax_REST.cow_model.MineralInfo


enum class CowmodelURLPath(private val path: String,
                           override val method: RequestType,
                           override val responseKind: PayloadKind?,
                           override val payloadKind: PayloadKind?)
    : IURLPath {
    OPENDAIRYSIML("/opendairysiml", RequestType.GET, Single(OpenSolverResponse::class), null),
    CLOSEDAIRYSIML("/closedairysiml/{id}", RequestType.GET, Single(CloseSolverResponse::class), null),
    SETCOWPARAMETERS("/setcowparameters/{id}", RequestType.POST,  Single(CowParametersResponse::class), Single(CowInput::class)),
    GETCONTOURS("/getcontours", RequestType.GET, Single(ContoursResponse::class), null),
    GETBREEDS("/getbreeds", RequestType.GET, Single(BreedsResponse::class), null),
    GETMINERALS("/getminerals", RequestType.GET, Single(MineralsResponse::class), null),
    GETMJPERKGOFBREEDMILK("/getmjperkgofbreedmilk/{id}", RequestType.GET, Single(MjPerKgOfBreedMilkResponse::class), null),
    GETBREEDNAME("/getbreedname/{id}", RequestType.GET, Single(BreedNameResponse::class), null),
    MS2KGBREEDMILK("/ms2kgbreedmilk/{id}", RequestType.POST, Single(Ms2KgBreedMilkResponse::class), Single(KgMS::class)),
    KGBREEDMILK2MS("/kgbreedmilk2ms/{id}", RequestType.POST, Single(KgBreedMilk2MSResponse::class), Single(BreedMilk::class)),
    GETLINPROGPARAMETERS("/getlinprogparameters/{id}", RequestType.GET, Single(DelphiLinProgParametersResponse::class), null),
    GETBRDPERCMILKFAT("/getbrdpercmilkfat/{id}", RequestType.GET, Single(BrdPercMilkFatResponse::class), null),
    GETBRDPERCMILKPROT("/getbrdpercmilkprot/{id}", RequestType.GET, Single(BrdPercMilkProtResponse::class), null),
    GETBRDPERCMILKLACTOSE("/getbrdpercmilklactose/{id}", RequestType.GET, Single(BrdPercMilkLactoseResponse::class), null),
    GETBRDPERCMILKCASEIN("/getbrdpercmilkcasein/{id}", RequestType.GET, Single(BrdPercMilkCaseinResponse::class), null),
    GETBRDPERCMILKALBUMIN("/getbrdpercmilkalbumin/{id}", RequestType.GET, Single(BrdPercMilkAlbuminResponse::class), null),
    GETVALUEBWTCHANGE("/getvaluebwtchange/{id}", RequestType.POST, Single(ValueBwtChangeResponse::class), Single(ReplValue::class)),
    SETPAYMENTSYSTEM("/setpaymentsystem/{id}", RequestType.POST, Single(PaymentSystemResponse::class), Single(PaymentSystemInput::class)),
    GETMILKPRICEPERKG("/getmilkpriceperkg/{id}", RequestType.GET, Single(MilkPricePerKgResponse::class), null),
    GETDAIRYCOWPERIODS("/getdairycowperiods/{id}", RequestType.GET, Single(DairyCowPeriodsResponse::class), null),
    GETMATUREWEIGHT("/getmatureweight/{id}", RequestType.GET, Single(MatureWeightResponse::class), null),
    GETMILKPRICECONVERSIONFACTOR("/getmilkpriceconversionfactor/{id}", RequestType.GET, Single(MilkPriceConversionFactorResponse::class), null),

    ;

    override val route: String
        get() = this.path


}


