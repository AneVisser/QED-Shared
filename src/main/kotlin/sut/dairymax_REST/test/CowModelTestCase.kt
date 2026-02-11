package qed.testbaseclass.sut.dairymax_REST.test

import kotlinx.serialization.Serializable
import qed.sut.dairymax_REST.CowInput
import qed.testbaseclass.sut.dairymax_REST.cow_model.DelphiLinProgParametersResponse

@Serializable
data class CowModelTestCase(
    val input: CowInput,
    val output: DelphiLinProgParametersResponse
)