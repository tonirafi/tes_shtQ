package com.tes.tesshtq.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tes.tesshtq.home.ResponseHome


@Database(entities = arrayOf(ResponseHome.Produk::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun DAO(): DAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database_tes"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}



