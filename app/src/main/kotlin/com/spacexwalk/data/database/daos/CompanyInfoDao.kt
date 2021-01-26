package com.spacexwalk.data.database.daos

import androidx.room.*
import com.spacexwalk.data.database.models.CompanyInfo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * Created by olegsheliakin on 26.01.2021.
 */
@Dao
interface CompanyInfoDao {

    @Query("SELECT * FROM company_info LIMIT 1")
    fun stream(): Observable<CompanyInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(info: CompanyInfo): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(info: CompanyInfo): Completable
}
