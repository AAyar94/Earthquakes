package com.aayar94.earthquakes.di

import android.content.Context
import androidx.room.Room
import com.aayar94.earthquakes.data.localdb.EarthquakeDao
import com.aayar94.earthquakes.data.localdb.EarthquakeDatabase
import com.aayar94.earthquakes.utils.Constant.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EarthquakeDatabase {
        return Room.databaseBuilder(
            context,
            EarthquakeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideEarthquakeDao(database: EarthquakeDatabase): EarthquakeDao {
        return database.getEarthquakeDao()
    }

}