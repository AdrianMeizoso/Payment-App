package com.adrian.payment.common.injection

import com.adrian.payment.main.repository.RunsRepository
import com.adrian.payment.main.datasource.RunsApiDataSource
import com.adrian.payment.main.datasource.RunsDataSource
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    companion object {
        const val BASE_URL = "http://www.speedrun.com/api/v1/"
    }

    @Provides
    @Singleton
    fun bindOkHttpClient(): OkHttpClient = OkHttpClient()

    @Provides
    @Singleton
    fun bindRetrofit(okHttpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()

    @Provides
    @Singleton
    fun bindMoshi(): Moshi =
            Moshi.Builder().build()

    @Provides
    @Singleton
    fun bindGamesApiDataSource(retrofit: Retrofit): RunsApiDataSource
            = retrofit.create(RunsApiDataSource::class.java)

    @Provides
    @Singleton
    fun bindGamesRepository(runsApiDataSource: RunsApiDataSource): RunsDataSource
            = RunsRepository(runsApiDataSource)
}