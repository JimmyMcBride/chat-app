/**
 * Created by Jimmy McBride on 2023-07-25
 *
 * Copyright © 2023 Jimmy McBride
 */
package dev.jimmymcbride.chatapp.domain

/**
 * SignInResult
 *
 * @author Jimmy McBride on 2023-07-25.
 */
data class SignInResult(
 val data: UserData?,
 val errorMessage: String?
)
