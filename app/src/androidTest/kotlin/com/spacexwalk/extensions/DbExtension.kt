package com.spacexwalk.extensions

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.spacexwalk.data.database.Database
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * Created by olegsheliakin on 26.01.2021.
 */
class DbExtension : BeforeEachCallback, AfterEachCallback {

    lateinit var database: Database

    override fun beforeEach(context: ExtensionContext?) {
        val appContext = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(appContext, Database::class.java).build()
    }

    override fun afterEach(context: ExtensionContext?) {
        database.apply {
            clearAllTables()
            close()
        }
    }
}
