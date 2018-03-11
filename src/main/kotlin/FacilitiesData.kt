import java.io.File

class FacilitiesData(val size: Int, val flowMatrix: Matrix, val distanceMatrix: Matrix) {

    companion object {
        fun readFrom(pathname: String): FacilitiesData {
            val file = File(pathname)
            val content = file.readText().trim().split(Regex("\\n\\s*\\n"))
            val size = content[0].toInt()
            val flowMatrix = content[1].toMatrix()
            val distanceMatrix = content[2].toMatrix()
            return FacilitiesData(size, flowMatrix, distanceMatrix)
        }

        private fun String.toMatrix() = toMatrixRows().map {
            it.split(Regex("\\s\\s")).map { it.toInt() }
        }

        private fun String.toMatrixRows() = split(Regex("\\n")).map { it.trim() }
    }
}
