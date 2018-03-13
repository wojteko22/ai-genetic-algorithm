class ChartSize(numberOfFacilities: Int) {
    
    val bottom = when(numberOfFacilities) {
        12 -> 1650.0
        14 -> 1650.0
        16 -> 1650.0
        18 -> 1650.0
        20 -> 1650.0
        else -> error("No such data")
    }

    val top = when(numberOfFacilities) {
        12 -> 2050.0
        14 -> 2100.0
        16 -> 2100.0
        18 -> 2100.0
        20 -> 2100.0
        else -> error("No such data")
    }
}