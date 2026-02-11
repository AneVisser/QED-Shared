package qed.testbaseclass.sut.dairymax_REST.ktor_responses

import qed.sut.dairymax_REST.LinProgParametersResponse

/**
 * Common interface for user-defined animal groupings (production groups and youngstock groups).
 * Implemented by DIMRange and YoungstockGroup.
 */
interface IFarmManagementGroup {
    val name: String
    val animalGroups: List<ILPAnimalGroup>
    val showMilkMetrics: Boolean  // false for youngstock, true for milking groups
    fun addAnimalGroup(group: ILPAnimalGroup)  // Add this
}

/**
* The cow model parameters are automatically remapped from lactation periods
* to LP periods based on the calving month and LP start month.
*/
interface ILPAnimalGroup {
    val id: Int
    val type: IAnimalGroupType
    val headCount: List<Int>  // indexed by LP period (0-11)
    val linProgParameters: LinProgParametersResponse
    val lpStartMonth: ICalendarMonth
    var farmManagementGroup: IFarmManagementGroup?
    fun assignToFarmManagementGroup(group: IFarmManagementGroup)
}

interface IAnimalGroupType {
        val shortCode: String
        val description: String
}