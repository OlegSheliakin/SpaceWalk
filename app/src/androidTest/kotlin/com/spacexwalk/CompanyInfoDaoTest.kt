package com.spacexwalk

import com.spacexwalk.data.database.daos.CompanyInfoDao
import com.spacexwalk.extensions.DbExtension
import com.spacexwalk.extensions.InstantExecutorExtension
import com.spacexwalk.factories.CompanyInfoFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Test for [CompanyInfoDao]
 *
 * Created by olegsheliakin on 26.01.2021.
 */
@ExtendWith(InstantExecutorExtension::class)
class CompanyInfoDaoTest {

    private val companyInfoDao by lazy { dbExtension.database.companyInfoDao() }

    @Test
    fun replaceTest() {
        val expected = CompanyInfoFactory.companyInfo()

        companyInfoDao.replace(expected)

        val actual = companyInfoDao.stream().blockingFirst()

        assertEquals(expected, actual)
    }

    @Test
    fun clearTest() {
        val expected = CompanyInfoFactory.companyInfo()

        companyInfoDao.insert(expected)
        companyInfoDao.clear()

        companyInfoDao.stream().test().assertEmpty()
    }

    @Test
    fun streamTest() {
        val expected = CompanyInfoFactory.companyInfo()
        val nextExpected = expected.copy(founderName = "Oleg Sheliakin")

        val testObserver = companyInfoDao.stream().test()
        companyInfoDao.replace(expected)
        companyInfoDao.replace(nextExpected)

        testObserver.assertValues(expected, nextExpected)
    }

    @Test
    fun insertTest() {
        val expected = CompanyInfoFactory.companyInfo()
        val nextExpected = expected.copy(founderName = "Oleg Sheliakin")

        companyInfoDao.insert(expected)
        companyInfoDao.insert(nextExpected)

        val actual = companyInfoDao.stream().blockingFirst()

        assertEquals(nextExpected, actual)
    }

    companion object {
        @JvmField
        @RegisterExtension
        val dbExtension = DbExtension()
    }
}
