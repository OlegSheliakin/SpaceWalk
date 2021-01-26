package com.spacexwalk.factories

import com.spacexwalk.data.database.models.Rocket

/**
 * Factory for [Rocket]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
object RocketFactory {

    fun rocket(
        id: String = "5e9d0d95eda69955f709d1eb",
        name: String = "Falcon 1",
        type: String = "rocket"
    ) = Rocket(
        id = id,
        name = name,
        type = type
    )

    fun rockets(
        amount: Int,
        idCreator: (Int) -> String = { it.toString() },
    ): Sequence<Rocket> {
        val localAmount = if (amount < 0) 0 else amount

        return (0..localAmount).asSequence().map { id ->
            rocket(id = idCreator.invoke(id))
        }
    }
}
