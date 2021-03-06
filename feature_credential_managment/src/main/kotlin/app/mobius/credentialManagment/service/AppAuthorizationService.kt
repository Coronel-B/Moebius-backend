package app.mobius.credentialManagment.service

import app.mobius.credentialManagment.data.repository.AppAuthorizationJpaRepository
import app.mobius.credentialManagment.domain.entity.security.Platform
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.*

@Service
class   AppAuthorizationService {

    @Autowired
    private lateinit var appAuthorizationJpaRepository: AppAuthorizationJpaRepository

    /**
     * @param privateKey: Password to validate
     */
    fun isValidAppAuthorization(platform: Platform, developerName: String, privateKey: String) : Boolean {
        return appAuthorizationJpaRepository.isValidAppAuthorization(
                UUID.fromString(
                        findAppAuthorizationDeveloperUUID(platform, developerName)
                ),
                privateKey
        )
    }

    private fun findAppAuthorizationDeveloperUUID(platform: Platform, developer: String) : String {
        return appAuthorizationJpaRepository.findAppAuthorizationDeveloperUUID(platform, developer)
    }

}