package com.spacexwalk.data.database.daos

import androidx.room.*
import com.spacexwalk.data.database.models.Launch
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * Created by olegsheliakin on 26.01.2021.
 */
@Dao
interface LaunchesDao {

    @Query("SELECT * FROM launch")
    fun stream(): Observable<List<Launch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(launch: Launch): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(launches: List<Launch>)

    @Query("DELETE FROM launch")
    fun clearTable()

    @Transaction
    fun replaceAll(entities: List<Launch>) {
        clearTable()
        insert(entities)
    }
}
