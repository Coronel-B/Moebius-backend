package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

/**
 * Map with:
 *  . AppConsumerUsers
 *  . AppConsumerPartner
 *  . AppConsumerOrganization
 */
@Entity
@Table(name = "application")
data class Application(
        @Id val applicationUUID: UUID,
        val environment: Environment,
        val consumer: Consumer,
        val publicKey: String
)

enum class Environment {
    DEV, INTEGRATION, TESTING, STAGING, PRODUCTION
}


sealed class Consumer {
    @Entity
    @Table(name = "consumer_identities")
    data class Identities(
            @Id val usersUUID: UUID,
            val platform: Platform)

    /**
     * A partner consumes a particular feature
     */
    @Entity
    @Table(name = "consumer_partner")
    data class Partner(
            @Id val partnerUUID: UUID,
            val name: String,
            val platform: Platform,
            val feature: String)

    /**
     * A team consumes a particular feature
     */
    @Entity
    @Table(name = "consumer_team")
    data class Team(
            @Id val teamUUID: UUID,
            val name: String,
            val platform: Platform,
            val feature: String)
}

@Entity
@Table(name = "platform")
data class Platform(
        @Id val platformUUID: UUID,
        val name: String,
        val ecosystem: String
)