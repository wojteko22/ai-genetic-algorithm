fun main(args: Array<String>) {
    val params = AlgorithmParams()
    val facilitiesData = FacilitiesData.readFrom("src/main/resources/had12.dat")
    AvgFamilyStats(params, facilitiesData).writeToCsv()
}
