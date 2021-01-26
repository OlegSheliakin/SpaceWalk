package com.spacexwalk

import com.spacexwalk.data.database.daos.LaunchesDao
import com.spacexwalk.extensions.DbExtension
import com.spacexwalk.extensions.InstantExecutorExtension
import com.spacexwalk.factories.LaunchFactory
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Test for [LaunchesDao]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
@ExtendWith(InstantExecutorExtension::class)
class LaunchesDaoTest {

    private val launchesDao by lazy { dbExtension.database.launchesDao() }

    @Test
    fun insertTest() {
        val expected = LaunchFactory.launch()

        launchesDao.insert(expected)

        launchesDao.stream()
            .test()
            .assertValue(listOf(expected))
    }

    @Test
    fun insertReplaceTest() {
        val expected = LaunchFactory.launch()
        val nextExpected = expected.copy(rocketId = "new_rocket_id")

        val testObserver = launchesDao.stream().test()

        launchesDao.insert(expected)
        launchesDao.insert(nextExpected)

        testObserver.assertValues(
            emptyList(),
            listOf(expected),
            listOf(nextExpected)
        )
    }

    @Test
    fun insertListTest() {
        val expected = LaunchFactory.launches(amount = 5).toList()

        launchesDao.insert(expected)

        launchesDao.stream().test().assertValue(expected)
    }

    @Test
    fun insertReplaceListTest() {
        val expected = LaunchFactory.launches(amount = 5).toList()
        val nextExpected = expected.mapIndexed { index, launch -> launch.copy(rocketId = launch.rocketId + index) }

        launchesDao.insert(expected)
        launchesDao.insert(nextExpected)

        launchesDao.stream().test().assertValue(nextExpected)
    }

    @Test
    fun insertDuplicatesTest() {
        val duplicates = LaunchFactory.launches(amount = 5) { "the_same_id" }.toList()
        val expected = duplicates.distinct()

        launchesDao.insert(duplicates)

        launchesDao.stream().test()
            .assertValue(expected)
            .assertValue { it.size == 1 }
    }

    @Test
    fun clearTest() {
        val expected = LaunchFactory.launches(amount = 2).toList()

        launchesDao.insert(expected)
        launchesDao.clear()

        launchesDao.stream().test().assertValue { it.isEmpty() }
    }

    @Test
    fun streamTest() {
        val first = LaunchFactory.launch(name = "First")
        val second = LaunchFactory.launch(name = "Second")
        val replaced = LaunchFactory.launches(amount = 5).toList()

        val testObserver = launchesDao.stream().test()

        launchesDao.insert(first)
        launchesDao.insert(second)
        launchesDao.replaceAll(replaced)

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
