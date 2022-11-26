package com.trade.upstox.di

import com.trade.upstox.resource.HoldingsRepo
import com.trade.upstox.resource.HoldingsUseCase
import com.trade.upstox.resource.IHoldingsRepo
import com.trade.upstox.resource.IHoldingsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class AbstractDependencyModule {

    @Binds
    abstract fun provideRepo(repo: HoldingsRepo): IHoldingsRepo

    @Binds
    abstract fun provideUseCase(useCase: HoldingsUseCase): IHoldingsUseCase
}