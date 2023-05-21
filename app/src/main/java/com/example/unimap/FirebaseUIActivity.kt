package com.example.unimap


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import com.codelab.android.datastore.data.UserPreferencesRepository
import com.example.unimap.Fragments.ProfileFragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class FirebaseUIActivity() : AppCompatActivity(){
    private val Context.dataStore by preferencesDataStore(
        name = "settings"
    )
private val userPreferencesRepository = UserPreferencesRepository(dataStore)
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract(),
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_ui)

        createSignInIntent()
    }

    private fun createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
        )

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
        // [END auth_fui_create_intent]
    }

    // [START auth_fui_result]
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            GlobalScope.launch {

                userPreferencesRepository.saveNameAndEmail(
                    user?.displayName ?: "",
                    user?.email ?: ""
                )

               val user = userPreferencesRepository.mapUserPreferences( dataStore.data.first().toPreferences() )
                println("user email" + user.email)
            }


            val tabIntent: Intent = Intent(this, TabActivity::class.java)
            startActivity(tabIntent)
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    suspend fun saveUserDetails(email: String, name: String) {
//        val exampleCounterFlow: Flow<Int> = context.dataStore.data
//            .map { preferences ->
//                // No type safety.
//
//            }
//        Context.dataStore.data.map { preferences ->
//            preferences[PreferencesKeys.EMAIL] = email
//            preferences[PreferencesKeys.USER_NAME] = name
//        }
    }
    // [END auth_fui_result]


    private fun delete() {
        // [START auth_fui_delete]
        AuthUI.getInstance()
            .delete(this)
            .addOnCompleteListener {
                // ...
            }
        // [END auth_fui_delete]
    }

    private fun themeAndLogo() {
        val providers = emptyList<AuthUI.IdpConfig>()

        // [START auth_fui_theme_logo]
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.i) // Set logo drawable
            .setTheme(R.style.Theme_UniMap) // Set theme
            .build()
        signInLauncher.launch(signInIntent)
        // [END auth_fui_theme_logo]
    }

    private fun privacyAndTerms() {
        val providers = emptyList<AuthUI.IdpConfig>()
        // [START auth_fui_pp_tos]
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTosAndPrivacyPolicyUrls(
                "https://example.com/terms.html",
                "https://example.com/privacy.html",
            )
            .build()
        signInLauncher.launch(signInIntent)
        // [END auth_fui_pp_tos]
    }

//    open fun emailLink() {
//        // [START auth_fui_email_link]
//        val actionCodeSettings = ActionCodeSettings.newBuilder()
//            .setAndroidPackageName( // yourPackageName=
//                "...", // installIfNotAvailable=
//                true, // minimumVersion=
//                null,
//            )
//            .setHandleCodeInApp(true) // This must be set to true
//            .setUrl("https://google.com") // This URL needs to be whitelisted
//            .build()
//
//        val providers = listOf(
//            EmailBuilder()
//                .enableEmailLinkSignIn()
//                .setActionCodeSettings(actionCodeSettings)
//                .build(),
//        )
//        val signInIntent = AuthUI.getInstance()
//            .createSignInIntentBuilder()
//            .setAvailableProviders(providers)
//            .build()
//        signInLauncher.launch(signInIntent)
//        // [END auth_fui_email_link]
//    }

//    open fun catchEmailLink() {
//        val providers: List<AuthUI.IdpConfig> = emptyList()
//
//        // [START auth_fui_email_link_catch]
//        if (AuthUI.canHandleIntent(intent)) {
//            val extras = intent.extras ?: return
//            val link = extras.getString("email_link_sign_in")
//            if (link != null) {
//                val signInIntent = AuthUI.getInstance()
//                    .createSignInIntentBuilder()
//                    .setEmailLink(link)
//                    .setAvailableProviders(providers)
//                    .build()
//                signInLauncher.launch(signInIntent)
//            }
//        }
//        // [END auth_fui_email_link_catch]
//    }
}

