/**
 * Created by Jimmy McBride on 2023-07-25
 *
 * Copyright © 2023 Jimmy McBride
 */
package dev.jimmymcbride.chatapp.presentation.screens.sign_in

/**
 * SignInState
 *
 * @author Jimmy McBride on 2023-07-25.
 */
data class SignInState(
  val isSignInSuccessful: Boolean = false,
  val signInError: String? = null
)
