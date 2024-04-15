package com.example.a3andm.local

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.a3andm.api.recettes.Recette
import com.example.a3andm.local.dao.RecetteDao
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Database(entities = [Recette::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recetteDao(): RecetteDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .build()
                    .also { instance = it }
            }
    }
}

class Converters {
    @TypeConverter
    fun fromList(value: List<String>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return Json.decodeFromString(value)
    }
}