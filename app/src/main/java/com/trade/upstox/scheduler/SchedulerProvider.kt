package com.trade.upstox.scheduler

import kotlinx.coroutines.CoroutineDispatcher

interface SchedulerProvider {

    val main: CoroutineDispatcher

    val default: CoroutineDispatcher

    val IO: CoroutineDispatcher

}