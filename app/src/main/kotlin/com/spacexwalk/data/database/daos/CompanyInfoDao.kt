package com.spacexwalk.data.database.daos

import androidx.room.*
import com.spacexwalk.data.database.models.CompanyInfo
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by olegsheliakin on 26.01.2021.
 */
@Dao
interface CompanyInfoDao {

    @Query("SELECT * FROM company_info LIMIT 1")
    fun stream(): Flowable<CompanyInfo>

    @Query("SELECT * FROM company_info LIMIT 1")
    fun get(): Maybe<CompanyInfo>

    @Query("DELETE FROM company_info")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(info: CompanyInfo)

    @Transaction
    fun replace(info: CompanyInfo) {
        clear()
        insert(info)
    }
}
