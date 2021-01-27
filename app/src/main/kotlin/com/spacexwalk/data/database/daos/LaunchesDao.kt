package com.spacexwalk.data.database.daos

import androidx.room.*
import com.spacexwalk.data.database.models.Launch
import io.reactivex.Flowable
import io.reactivex.Maybe

/**
 * Created by olegsheliakin on 26.01.2021.
 */
@Dao
interface LaunchesDao {

    @Query("SELECT * FROM launch")
    fun stream(): Flowable<List<Launch>>

    @Query("SELECT * FROM launch")
    fun getAll(): Maybe<List<Launch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(launch: Launch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(launches: List<Launch>)

    @Query("DELETE FROM launch")
    fun clear()

    @Transaction
    fun replaceAll(entities: List<Launch>) {
        clear()
        insert(entities)
    }
}
