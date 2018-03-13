class ChartSize(numberOfFacilities: Int) {
    
    val bottom = when(numberOfFacilities) {
        12 -> 1650.0
        14 -> 2700.0
        16 -> 3700.0
        18 -> 5300.0
        20 -> 6900.0
        else -> error("No such data")
    }

    val top = when(numberOfFacilities) {
        12 -> 2050.0
        14 -> 3400.0
        16 -> 4500.0
        18 -> 6300.0
        20 -> 8200.0
        else -> error("No such data")
    }
}