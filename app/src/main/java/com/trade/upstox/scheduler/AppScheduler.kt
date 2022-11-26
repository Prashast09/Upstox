package com.trade.upstox.scheduler

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Provides Scheduler to be injected by [AppModule]
 */
class AppSchedulerProvider : SchedulerProvider {

    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    override val IO: CoroutineDispatcher
        get() = Dispatchers.IO

}