package com.phoenix.dikoin.di_module

import com.phoenix.dikoin.MainActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityModule = module {
    scope<MainActivity> {
        scoped(qualifier = named("hello")) { "Hello" }
        scoped(qualifier = named("bey"))  { "Bey" }
    }
}