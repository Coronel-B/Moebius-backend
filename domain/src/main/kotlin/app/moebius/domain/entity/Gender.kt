package app.moebius.domain.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "gender")
data class Gender(
        @Id val genderUUID: UUID,
        val type: String,
        val description: String,
        val iconUrl: String
)