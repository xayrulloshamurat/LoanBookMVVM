package com.example.loan_book_mvvm

import android.app.Application
import com.example.loan_book_mvvm.di.dataModul
import com.example.loan_book_mvvm.di.helperModul
import com.example.loan_book_mvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val modules =  listOf(dataModul, helperModul , viewModelModule)
        startKoin { androidLogger()

            androidContext(this@App)

            androidFileProperties()

            koin.loadModules(modules)
        }
    }
}