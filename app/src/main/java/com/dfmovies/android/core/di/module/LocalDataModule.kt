package com.dfmovies.android.core.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.dfmovies.android.core.di.qualifier.ApplicationContext
import com.dfmovies.android.core.domain.persistence.DfMoviesDatabase
import com.dfmovies.android.main.data.source.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): DfMoviesDatabase {
        return Room
                .databaseBuilder(
                        context, DfMoviesDatabase::class.java,
                        CONTENT_DB_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(database: DfMoviesDatabase): MovieDao {
        return database.movieDao
    }

    companion object {
        private const val CONTENT_DB_NAME = "df_content.db"
        private const val PREF_NAME = "PREF"
    }
}