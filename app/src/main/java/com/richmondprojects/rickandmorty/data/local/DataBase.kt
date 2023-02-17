package com.richmondprojects.rickandmorty.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ResultsEntity::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract val dao: ResultsDao

}