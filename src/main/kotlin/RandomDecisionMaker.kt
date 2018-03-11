import java.util.*

object RandomDecisionMaker {

    private val random = Random()

    fun shouldTakeAction(odds: Float) = random.nextFloat() < odds
}
