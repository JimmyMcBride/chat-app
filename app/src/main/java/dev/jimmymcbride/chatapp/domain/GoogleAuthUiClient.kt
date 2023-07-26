/**
 * Created by Jimmy McBride on 2023-07-25
 *
 * Copyright © 2023 Jimmy McBride
 */
package dev.jimmymcbride.chatapp.domain

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

/**
 * GoogleAuthUiClient
 *
 * @author Jimmy McBride on 2023-07-25.
 */
class GoogleAuthUiClient(
  private val context: Context,
  private val oneTapClient: SignInClient,
) {
  private val auth = Firebase.auth

  suspend fun signIn(): IntentSender? {
    val result = try {
      oneTapClient.beginSignIn(
        buildSignInRequest()
      ).await()
    } catch (e: Exception) {
      e.printStackTrace()
      if (e is CancellationException) throw e
      null
    }
    return result?.pendingIntent?.intentSender
  }

  suspend fun signInWithIntent(intent: Intent): SignInResult {
    val credential = oneTapClient.getSignInCredentialFromIntent(intent)
    val googleIdToken = credential.googleIdToken
    val googleCredentials =
      GoogleAuthProvider.getCredential(googleIdToken, null)
    return try {
      val user = auth.signInWithCredential(googleCredentials).await().user
      SignInResult(
        data = user?.run {
          UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString()
          )
        },
        errorMessage = null
      )
    } catch (e: Exception) {
      e.printStackTrace()
      if (e is CancellationException) throw e
      SignInResult(
        data = null,
        errorMessage = e.message
      )
    }
  }

  suspend fun signOut() {
    try {
      oneTapClient.signOut().await()
      auth.signOut()
    } catch (e: Exception) {
      e.printStackTrace()
      if (e is CancellationException) throw e
    }
  }

  fun getSignedInUser(): UserData? = auth.currentUser?.run {
    UserData(
      userId = uid,
      username = displayName,
      profilePictureUrl = photoUrl?.toString()
    )
  }

  private fun buildSignInRequest(): BeginSignInRequest {
    return BeginSignInRequest.Builder()
      .setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
          .setSupported(true)
          .setFilterByAuthorizedAccounts(false)
          .setServerClientId("932456491672-3htqcd77vranicvv7j4jnv22iipudmue.apps.googleusercontent.com")
          .build()
      )
      .setAutoSelectEnabled(true)
      .build()
  }
}
