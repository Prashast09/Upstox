package com.trade.upstox.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.trade.upstox.scheduler.AppSchedulerProvider
import com.trade.upstox.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    internal fun provideSchedulers(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}