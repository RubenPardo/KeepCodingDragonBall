package com.example.keepcodingdragonball
import android.app.Application
import com.example.keepcodingdragonball.di.dataModule
import com.example.keepcodingdragonball.di.domainModel
import com.example.keepcodingdragonball.di.presentationModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(if(BuildConfig.DEBUG) Level.INFO else Level.NONE)
            androidContext(this@Application)
            modules(listOf(
                presentationModule,
                domainModel,
                dataModule
            ))
        }
    }

}