package app.moebius.domain.entity.security

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * @param securityLevel: [0,4]
 */
@Entity
@Table(name = "security")
data class Security(
        @Id val securityUUID: UUID,
        val authentication: Authentication,
        val securityLevel: Int = 0,
        val securityMethods: SecurityMethods? = null,
)

/**
 * Represents a authentication credential
 * TODO: Add app token
 */
@Entity
@Table(name = "authentication")
data class Authentication(
        @Id val authenticationUUID: UUID,
        val session: Session,
        val basicAuth: BasicAuth
)

/**
 * Represents a traditional authentication
 * OBS: Other methods will not be considered
 */
@Entity
@Table(name = "traditional_credential")
data class BasicAuth(
        @Id val traditionalCredentialUUID: UUID,
        val email: String,
        val password: Password,
)

@Entity
@Table(name = "password")
data class Password(
        @Id val passwordUUID: UUID,
        val apiHashPassword: String,
        val dbHashPassword: String,
        val resetPasswordToken: String? = null,
        val resetPasswordTokenExpire: String? = null,
)

// TODO: Add more credentials

@Entity
@Table(name = "email")
data class Email(
        @Id val emailUUID: UUID
)
