/**
 * Created by Jimmy McBride on 2023-07-25
 *
 * Copyright © 2023 Jimmy McBride
 */
package dev.jimmymcbride.chatapp.presentation.screens.sign_in

import androidx.lifecycle.ViewModel
import dev.jimmymcbride.chatapp.domain.SignInResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * SignInViewModel
 *
 * @author Jimmy McBride on 2023-07-25.
 */
class SignInViewModel : ViewModel() {

  private val _state = MutableStateFlow(SignInState())
  val state = _state.asStateFlow()

  fun onSignInResult(result: SignInResult) {
    _state.update {
      it.copy(
        isSignInSuccessful = result.data != null,
        signInError = result.errorMessage
      )
    }
  }

  fun resetState() {
    _state.update { SignInState() }
  }
}
