package app.moebius.domain.entity

import app.moebius.domain.entity.security.Security
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "account")
data class Account(
        val accountUUID: UUID,
        val traditionalCredential: TraditionalCredential,
        val security: Security? = null,
        val token: String,  // TODO: Should be?
        val linkedAccounts: LinkedAccounts? = null
)

@Entity
@Table(name = "traditional_credential")
data class TraditionalCredential(
        val traditionalCredentialUUID: UUID,
        val email: String,
        val password: Password,
)

@Entity
@Table(name = "password")
data class Password(
        val passwordUUID: UUID,
        val apiHashPassword: String,
        val dbHashPassword: String,
        val resetPasswordToken: String? = null,
        val resetPasswordTokenExpire: String? = null,
)

// TODO: Add more credentials

data class Email(
        val emailUUID: UUID
)