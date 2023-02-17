package com.richmondprojects.rickandmorty.di

import android.content.Context
import androidx.room.Room
import com.richmondprojects.rickandmorty.data.local.DataBase
import com.richmondprojects.rickandmorty.data.remote.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val client = OkHttpClient.Builder().addInterceptor(logging).build()

    @Provides
    @Singleton
    fun providesRetrofit(): Api {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/").client(client)
            .build().create(Api::class.java)
    }

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(
            context, DataBase::class.java, "rick_and_morty_database"
        ).build()
    }
}