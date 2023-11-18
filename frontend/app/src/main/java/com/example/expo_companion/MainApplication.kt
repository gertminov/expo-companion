package com.example.expo_companion

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.preference.PreferenceManager
import dagger.hilt.android.HiltAndroidApp
import no.entur.android.nfc.external.ExternalNfcServiceAdapter
import no.entur.android.nfc.external.acs.service.AcsUsbService

@HiltAndroidApp
class MainApplication : Application() {
    var adapter: ExternalNfcServiceAdapter? = null
    override fun onCreate() {
        super.onCreate()
        adapter = ExternalNfcServiceAdapter(this, AcsUsbService::class.java, false)
    }

    @SuppressLint("CommitPrefEdits")
    fun setExternalNfcReader(enabled: Boolean) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preferences.edit()
        editor.putBoolean("externalNfcService", enabled)
        if (!editor.commit()) {
            throw IllegalStateException("Could not edit preferences for external NFC Reader")
        }

        if (enabled) {
            adapter?.startService(Bundle())
        } else {
            adapter?.stopService()
        }
    }
}