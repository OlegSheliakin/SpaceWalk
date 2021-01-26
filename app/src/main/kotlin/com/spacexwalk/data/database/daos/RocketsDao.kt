package com.spacexwalk.data.database.daos

import androidx.room.*
import com.spacexwalk.data.database.models.Rocket
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by olegsheliakin on 26.01.2021.
 */
@Dao
interface RocketsDao {

    @Query("SELECT * FROM rocket")
    fun stream(): Observable<List<Rocket>>

    @Query("SELECT * FROM rocket WHERE id = :id")
    fun getById(id: String): Single<Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocket: Rocket)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rockets: List<Rocket>)

    @Query("DELETE FROM rocket")
    fun clear()

    @Transaction
    fun replaceAll(entities: List<Rocket>) {
        clear()
        insert(entities)
    }
}
