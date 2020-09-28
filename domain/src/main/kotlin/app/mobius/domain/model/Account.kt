package app.mobius.domain.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class Account(
        @Id @GeneratedValue @Column(name = "account_uuid") val accountUUID: UUID? = null,
        val linkedAccounts: LinkedAccounts? = null,
)