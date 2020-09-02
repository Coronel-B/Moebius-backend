package app.moebius.domain.entity

import java.util.*

data class LinkedAccounts(
        val linkedAccountUUID: UUID,
        val facebook: Facebook? = null
)

data class Facebook(
        val facebookUUID: UUID,
        val username: String,
        val facebookId: Long? = null
)