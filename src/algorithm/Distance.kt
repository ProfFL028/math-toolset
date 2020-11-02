package algorithm

import kotlin.math.abs
import kotlin.math.pow

/**
 * Calc distances
 */
class Distance {
    companion object {
        /**
         * calc manhattan distance using minkoski distance where r=1
         * @param rating1
         * @param rating2
         * @return
         */
        fun manhattan(rating1: Map<String, Number>, rating2: Map<String, Number>): Double {
            return minkowski(rating1, rating2, 1)
        }

        /**
         * calculate minkowski distance
         * @param rating1
         * @param rating2
         * @param r
         * @return
         */
        fun minkowski(rating1: Map<String, Number>, rating2: Map<String, Number>, r: Number): Double {
            var distance = 0.0
            rating1.forEach { (key, value) ->
                if (rating2.containsKey(key)) {
                    var value2 = rating2[key]?.toDouble() ?: 0.0
                    distance += abs(value.toDouble() - value2).pow(r.toDouble())
                }
            }

            return distance.pow(1.0 / r.toDouble())
        }
    }
}
