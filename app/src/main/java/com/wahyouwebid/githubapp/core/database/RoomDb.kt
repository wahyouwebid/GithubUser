package com.wahyouwebid.githubapp.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wahyouwebid.githubapp.features.favourite.data.FavouriteDao
import com.wahyouwebid.githubapp.features.favourite.data.model.FavouriteEntity
import com.wahyouwebid.githubapp.features.popular.data.local.PopularDao
import com.wahyouwebid.githubapp.features.popular.data.model.PopularEntity

@Database(
    entities = [PopularEntity::class, FavouriteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters
abstract class RoomDb : RoomDatabase() {

    abstract fun popularDao(): PopularDao

    abstract fun favouriteDao(): FavouriteDao

    companion object {
        private const val DATABASE_NAME = "github_user"

        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getInstance(context: Context): RoomDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    DATABASE_NAME
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Reset auto-incremented key after creating the database
                            db.execSQL("DELETE FROM popular_entity")
                            db.execSQL("DELETE FROM sqlite_sequence WHERE name='popular_entity'")
                        }
                    })
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
