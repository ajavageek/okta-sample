package ch.frankel.blog.bootokta

import com.okta.sdk.client.Client
import com.okta.sdk.resource.user.User
import com.okta.sdk.resource.user.UserBuilder

class NewUser(val email: String, val password: String,
              val firstName: String, val lastName: String)

internal fun Client.create(user: NewUser): User = UserBuilder.instance()
    .setEmail(user.email)
    .setPassword(user.password.toCharArray())
    .setFirstName(user.firstName)
    .setLastName(user.lastName)
    .buildAndCreate(this)