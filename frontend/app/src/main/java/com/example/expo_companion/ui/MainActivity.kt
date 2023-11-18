package com.example.expo_companion.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.expo_companion.MainApplication
import com.example.expo_companion.data.questions.QuestionFetchState
import com.example.expo_companion.data.questions.Questions
import com.example.expo_companion.data.questions.UsedQuestions
import com.example.expo_companion.network.AuthApi
import com.example.expo_companion.network.QuestionApi
import com.example.expo_companion.ui.navigation.NavigationComponent
import com.example.expo_companion.ui.theme.ExpoCompanionTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import no.entur.android.nfc.external.*
import java.io.IOException
import java.util.concurrent.Executors

@AndroidEntryPoint
class MainActivity : ComponentActivity(),
    ExternalNfcTagCallback,
    ExternalNfcReaderCallback,
    ExternalNfcServiceCallback {

    private lateinit var nfcTagSupport: ExternalNfcTagCallbackSupport
    private lateinit var nfcReaderSupport: ExternalNfcReaderCallbackSupport
    private lateinit var nfcServiceSupport: ExternalNfcServiceCallbackSupport


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNFC()
        val mainApplication: MainApplication = application as MainApplication
        mainApplication.setExternalNfcReader(true)

        //authenticate the user and fetch questions
        lifecycleScope.launch {
            AuthApi.authUser()
            try {
                Questions.questions = QuestionApi.getQuestions() //TODO uses online version
                //--------------------Only Needed during dev--------
                //fill all tabs with questions
//                UsedQuestions.setQuestion(Questions.questions.values.first())
                //--------------------------------------------------
            } catch (e: IOException) {
                Questions.state.update { QuestionFetchState.ERROR }
            }
        }


//        keyboard changes dynamically when opened
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
//            https://github.com/Frank1234/ViewModelNavigationCompose nach Code von dem hier
            val navController = rememberNavController()
            ExpoCompanionTheme(false) {
                Scaffold {
                    NavigationComponent(navController, it)
                }
            }

        }
    }


    override fun onResume() {
        super.onResume()
        nfcTagSupport.onResume()
        nfcReaderSupport.onResume()
        nfcServiceSupport.onResume()

    }

    override fun onPause() {
        super.onPause()

        nfcTagSupport.onPause()
        nfcReaderSupport.onPause()
        nfcServiceSupport.onPause()

    }

    private fun setupNFC() {
        val executor = Executors.newCachedThreadPool()

        nfcTagSupport = ExternalNfcTagCallbackSupport(this, this, executor)
        nfcTagSupport.setEnabled(true)

        nfcReaderSupport = ExternalNfcReaderCallbackSupport(this, this, executor)
        nfcReaderSupport.setEnabled(true)

        nfcServiceSupport = ExternalNfcServiceCallbackSupport(this, this, executor)
        nfcServiceSupport.setEnabled(true)
    }


    override fun onTagDiscovered(p0: no.entur.android.nfc.wrapper.Tag?, p1: Intent?) {
        TODO("Not yet implemented")
    }

    override fun onExternalTagIDDiscovered(tagID: String, p1: Intent) {
        lifecycleScope.launch { UsedQuestions.onNewNfcIntent(tagID) }
        Log.i("EXNFC", "Discovered Tag: $tagID")
    }

    override fun onExternalNfcReaderOpened(intent: Intent) {
        Log.i("EXNFC", "opened NFC Reader")
    }

    override fun onExternalNfcReaderClosed(intent: Intent) {

    }

    override fun onExternalNfcServiceStopped(intent: Intent?) {
    }

    override fun onExternalNfcServiceStarted(intent: Intent?) {
        Log.i("EXNFC", "Started NFC service")
    }
}