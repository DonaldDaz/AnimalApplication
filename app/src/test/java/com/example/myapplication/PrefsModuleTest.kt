package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.PrefsModule
import com.example.myapplication.util.SharedPreferencesHelper

class PrefsModuleTest(val mockPrefs: SharedPreferencesHelper): PrefsModule() {

    override fun provideSharedPreferences(app: Application): SharedPreferencesHelper {
        return mockPrefs
    }

}