import algorithm.Distance
import org.junit.Test
import kotlin.test.assertEquals

class DistanceTest {
    companion object {
        var users = mutableMapOf<String, Map<String, Number>>(
                "Angelica" to mutableMapOf("Blues Traveler" to 3.5, "Broken Bells" to 2.0, "Norah Jones" to 4.5, "Phoenix" to 5.0, "Slightly Stoopid" to 1.5, "The Strokes" to 2.5, "Vampire Weekend" to 2.0),
                "Bill" to mutableMapOf("Blues Traveler" to 2.0, "Broken Bells" to 3.5, "Deadmau5" to 4.0, "Phoenix" to 2.0, "Slightly Stoopid" to 3.5, "Vampire Weekend" to 3.0),
                "Chan" to mutableMapOf("Blues Traveler" to 5.0, "Broken Bells" to 1.0, "Deadmau5" to 1.0, "Norah Jones" to 3.0, "Phoenix" to 5.0, "Slightly Stoopid" to 1.0),
                "Dan" to mutableMapOf("Blues Traveler" to 3.0, "Broken Bells" to 4.0, "Deadmau5" to 4.5, "Phoenix" to 3.0, "Slightly Stoopid" to 4.5, "The Strokes" to 4.0, "Vampire Weekend" to 2.0),
                "Hailey" to mutableMapOf("Broken Bells" to 4.0, "Deadmau5" to 1.0, "Norah Jones" to 4.0, "The Strokes" to 4.0, "Vampire Weekend" to 1.0),
                "Jordyn" to mutableMapOf("Broken Bells" to 4.5, "Deadmau5" to 4.0, "Norah Jones" to 5.0, "Phoenix" to 5.0, "Slightly Stoopid" to 4.5, "The Strokes" to 4.0, "Vampire Weekend" to 4.0),
                "Sam" to mutableMapOf("Blues Traveler" to 5.0, "Broken Bells" to 2.0, "Norah Jones" to 3.0, "Phoenix" to 5.0, "Slightly Stoopid" to 4.0, "The Strokes" to 5.0),
                "Veronica" to mutableMapOf("Blues Traveler" to 3.0, "Norah Jones" to 5.0, "Phoenix" to 4.0, "Slightly Stoopid" to 2.5, "The Strokes" to 3.0)
        )
    }

    @Test
    fun manhattanDistanceTest() {
        assertEquals(2.0, Distance.manhattan(users["Hailey"].orEmpty(), users["Veronica"].orEmpty()))
    }
    @Test
    fun maximumNormTest() {
        assertEquals(4.5, Distance.maximumNorm(users["Angelica"].orEmpty(), users["Bill"].orEmpty()))
    }

    @Test
    fun nearestNeighborTest() {
        var result = Distance.nearestNeighbor("Hailey", users, Distance.Companion::manhattan)
        assertEquals("Veronica", result?.key)
    }

    @Test
    fun recommendTest() {
        var recommendUser = Distance.recommend("Hailey", users, Distance.Companion::manhattan)
        assertEquals("{Phoenix=4.0, Blues Traveler=3.0, Slightly Stoopid=2.5}", recommendUser.toString())
    }
}
