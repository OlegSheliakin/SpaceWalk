package com.spacexwalk.factories

import com.spacexwalk.data.database.models.Launch
import com.spacexwalk.data.database.models.Rocket
import com.spacexwalk.domain.entities.Links
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Factory for [Rocket]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
object LaunchFactory {

    fun launch(
        name: String = "Falcon 1",
        dateUtc: ZonedDateTime = ZonedDateTime.now(ZoneId.of("UTC")),
        rocketId: String = "5e9d0d95eda69955f709d1eb",
        success: Boolean = true,
        staticFireDateUtc: ZonedDateTime? = ZonedDateTime.now(ZoneId.of("UTC"))
    ) = Launch(
        name = name,
        dateUtc = dateUtc,
        rocketId = rocketId,
        success = success,
        links = Links(
            smallIcon = "https://images2.imgbox.com/4f/e3/I0lkuJ2e_o.png",
            largeIcon = "https://images2.imgbox.com/be/e7/iNqsqVYM_o.png",
            presskit = null,
            article = "https://www.space.com/3590-spacex-falcon-1-rocket-fails-reach-orbit.html",
            wikipedia = "https://en.wikipedia.org/wiki/DemoSat",
            webCast = "https://www.youtube.com/watch?v=Lk4zQ2wP-Nc",
            youtubeId = "Lk4zQ2wP-Nc"
        ),
        details = "Details",
        isUpcoming = false,
        staticFireDateUtc = staticFireDateUtc
    )

    fun launches(
        amount: Int,
        nameCreator: (Int) -> String = { it.toString() },
    ): Sequence<Launch> {
        val localAmount = if (amount < 0) 0 else amount

        return (0..localAmount).asSequence().map { id ->
            launch(name = nameCreator.invoke(id))
        }
    }
}
