package com.tmr.chemx

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.auth.api.identity.Identity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.launchIn

@Composable
fun AuthScreen() {
    val auth = Firebase.auth
    val context = LocalContext.current
    val oneTapClient = remember {
        Identity.getSignInClient(context)
    }

    val googleSignInHelper = remember {
        GoogleSignInHelper(
            auth = auth,
            context = context,
            oneTapClient = oneTapClient
        )
    }

    val coroutineScope = rememberCoroutineScope()

    val isAuthenticated by remember(auth.currentUser) {
        derivedStateOf {
            auth.currentUser != null
        }
    }

    val signInWithGoogleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                googleSignInHelper.signInWithIntent(
                    result.data ?: return@rememberLauncherForActivityResult
                ).launchIn(coroutineScope)
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Is authenticated: $isAuthenticated")

        Button(
            onClick = {
                googleSignInHelper.createIntent {
                    signInWithGoogleLauncher.launch(
                        IntentSenderRequest.Builder(
                            it.intentSender
                        ).build()
                    )
                }
            }
        ) {
            Text(text = "Sign in with Google")
        }
    }
}