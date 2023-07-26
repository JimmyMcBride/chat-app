/**
 * Created by Jimmy McBride on 2023-07-25
 *
 * Copyright © 2023 Jimmy McBride
 */
package dev.jimmymcbride.chatapp.domain

/**
 * UserData
 *
 * @author Jimmy McBride on 2023-07-25.
 */
data class UserData(
 val userId: String,
 val username: String?,
 val profilePictureUrl: String?
)
