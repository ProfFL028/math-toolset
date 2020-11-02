package algorithm

import java.util.*
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
         * calc euclidean distance using minkoski distance where r=2
         * @param rating1
         * @param rating2
         * @return
         */
        fun euclidean(rating1: Map<String, Number>, rating2: Map<String, Number>): Double {
            return minkowski(rating1, rating2, 2)
        }

        /**
         * calc l-p norm distance of infinite
         */
        fun maximumNorm(rating1: Map<String, Number>, rating2: Map<String, Number>): Double {
            return rating1.map { (key, value) ->
                abs(value.toDouble() - (rating2[key]?.toDouble() ?: 0.0))
            }.max() ?: 0.0
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

        /**
         * find nearest neighbor
         * @param user
         * @param users
         * @param sortFunction
         * @return
         */
        fun nearestNeighbor(user: String, users: MutableMap<String, Map<String, Number>>,
                            sortFunction: (rating1: Map<String, Number>, rating2: Map<String, Number>) -> Double
        ): Map.Entry<String, Double>? {
            return users.filterKeys { key -> user != key }.mapValues { (key, value) ->
                sortFunction(users[user].orEmpty(), users[key].orEmpty())
            }.minBy { (key, value) -> value }
        }

        /**
         * return recommended
         */
        fun recommend(user: String, users: MutableMap<String, Map<String, Number>>,
                      sortFunction: (rating1: Map<String, Number>, rating2: Map<String, Number>) -> Double)
                : SortedMap<String, Number> {
            var recommendUser = nearestNeighbor(user, users, sortFunction)?.key
            if (users[recommendUser] != null) {
                return users[recommendUser]!!.filterKeys { key -> !users[user]!!.containsKey(key) }.toSortedMap(compareBy {
                    (users[recommendUser]!![it] ?: error("")).toDouble() * -1
                })
            }
            return sortedMapOf()
        }

    }
}
