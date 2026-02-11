package qed.testbaseclass.sut.dairymax_REST.lp_model

data class TreeTestResults(
    val totalTests: Int,
    val passed: Int,
    val failed: Int,
    val status: String,
    val tests: List<TestDetail>
)

data class TestDetail(
    val name: String,
    val passed: Boolean,
    val message: String,
    val duration: Int
)