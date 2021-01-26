package com.spacexwalk

import com.spacexwalk.data.database.daos.RocketsDao
import com.spacexwalk.extensions.DbExtension
import com.spacexwalk.extensions.InstantExecutorExtension
import com.spacexwalk.factories.RocketFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Test for [RocketsDao]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
@ExtendWith(InstantExecutorExtension::class)
class RocketDaoTest {

    private val rocketsDao by lazy { dbExtension.database.rocketsDao() }

    @Test
    fun insertTest() {
        val expected = RocketFactory.rocket()

        rocketsDao.insert(expected)

        rocketsDao.getById(expected.id)
            .test()
            .assertResult(expected)

        rocketsDao.stream()
            .test()
            .assertValue(listOf(expected))
    }

    @Test
    fun insertSameIdTest() {
        val expected = RocketFactory.rocket()
        val nextExpected = expected.copy(name = "Apollo 11")

        val testObserver = rocketsDao.stream().test()

        rocketsDao.insert(expected)
        rocketsDao.insert(nextExpected)

        testObserver.assertValues(
            emptyList(),
            listOf(expected),
            listOf(nextExpected)
        )
    }

    @Test
    fun insertListTest() {
        val expected = RocketFactory.rockets(amount = 5).toList()

        rocketsDao.insert(expected)

        rocketsDao.stream().test().assertValue(expected)
    }

    @Test
    fun insertDuplicatesTest() {
        val duplicates = RocketFactory.rockets(amount = 5) { "the_same_id" }.toList()
        val expected = duplicates.distinct()

        rocketsDao.insert(duplicates)

        rocketsDao.stream().test()
            .assertValue(expected)
            .assertValue { it.size == 1 }
    }

    @Test
    fun clearTest() {
        val expected = RocketFactory.rockets(amount = 2).toList()

        rocketsDao.insert(expected)
        rocketsDao.clear()

        rocketsDao.stream().test().assertValue { it.isEmpty() }
    }

    @Test
    fun streamTest() {
        val first = RocketFactory.rocket()
        val second = RocketFactory.rocket(name = "Apollo 11", id = "test_id")
        val replaced = RocketFactory.rockets(amount = 5).toList()

        val testObserver = rocketsDao.stream().test()

        rocketsDao.insert(first)
        rocketsDao.insert(second)
        rocketsDao.replaceAll(replaced)

        testObserver.assertValues(
            emptyList(),
            listOf(first),
            listOf(first, second),
            replaced,
        )
    }

    companion object {
        @JvmField
        @RegisterExtension
        val dbExtension = DbExtension()
    }
}
