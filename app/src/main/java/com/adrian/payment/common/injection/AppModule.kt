package com.adrian.payment.common.injection

import com.adrian.payment.common.injection.Url.BASE_URL
import com.adrian.payment.contacts.datasource.ContactsDeviceDataSource
import com.adrian.payment.contacts.datasource.ContactsFakeDataSource
import com.adrian.payment.contacts.datasource.HeroesApiDataSource
import com.adrian.payment.contacts.repository.ContactsRepository
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Url {
    const val BASE_URL = "http://gateway.marvel.com/v1/public/"
}

val appModule = module {
    single {
        OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                .readTimeout(5, TimeUnit.MINUTES).build()
    }

    single {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(get())
                .build()
    }

    single { Moshi.Builder().build() }
    single { ContactsDeviceDataSource(get()) }
    single { ContactsFakeDataSource() }
    single { get<Retrofit>().create(HeroesApiDataSource::class.java) }
    single { ContactsRepository(get<ContactsDeviceDataSource>(), get())}
}