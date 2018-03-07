import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class IndividualTest {
    private val inputData = InputData.readFrom("src/main/resources/had12.dat")

    @Test
    fun getCost() {
        val genes = listOf(3, 10, 11, 2, 12, 5, 6, 7, 8, 1, 4, 9)
        val individual = Individual(inputData, genes)
        assertThat(individual.cost).isEqualTo(1652)
    }
}