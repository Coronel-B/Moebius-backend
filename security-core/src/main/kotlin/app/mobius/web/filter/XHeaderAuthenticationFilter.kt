package app.mobius.web.filter

import app.mobius.credentialManagment.domain.entity.security.Platform
import app.mobius.domain.security.authorization.AppAuthorizationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Custom filter for the Spring Security filter chain.
 * Checks the request headers and the location for the Authorization header, starting with "Basic."
 *
 * Sources:
 *  . https://www.baeldung.com/spring-security-custom-filter
 *  . Add a filter: https://stackoverflow.com/q/19825946/5279996
 */
class XHeaderAuthenticationFilter: OncePerRequestFilter() {

    /**
     * Source:
     *  . Add filter to the spring config: https://stackoverflow.com/a/57341317/5279996
     *  . Get credentials from Basic Auth: https://stackoverflow.com/a/16000878/5279996
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val headerAuthorization = request.getHeader(HEADER_AUTHORIZATION)
        val headerPlatformName = request.getHeader(HEADER_PLATFORM_NAME)
        val headerPlatformEcosystem = request.getHeader(HEADER_PLATFORM_ECOSYSTEM)

        if ((headerAuthorization != null && headerAuthorization.toLowerCase().startsWith("basic"))||
                headerPlatformName != null ||
                headerPlatformEcosystem != null) {

            val base64Credentials = headerAuthorization.split("Basic ").last()
            val credentialsDecoded = String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8)
            val developer = credentialsDecoded.split(":").first()
            val password = credentialsDecoded.split(":").last()

            val authentication = AppAuthorizationToken(
                    developer = developer,
                    password = password,
                    platform = Platform(name = headerPlatformName, ecosystem = headerPlatformEcosystem)
            )


            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    companion object {
        private const val HEADER_AUTHORIZATION = "Authorization"
        private const val HEADER_PLATFORM_NAME = "Platform-Name"
        private const val HEADER_PLATFORM_ECOSYSTEM = "Platform-Ecosystem"
    }

}