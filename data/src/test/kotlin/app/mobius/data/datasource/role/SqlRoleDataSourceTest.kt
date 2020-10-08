package app.mobius.data.datasource.role

import app.mobius.data.di.HibernateUtil
import app.mobius.data.di.JDBM
import app.mobius.data.util.randomString
import app.mobius.domain.entity.role.*
import org.hibernate.Session
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SqlRoleDataSourceTest {

    private lateinit var hibernate : HibernateUtil
    private lateinit var session : Session

    @BeforeAll
    fun before() {
        hibernate = HibernateUtil()
    }

    @BeforeEach
    fun beforeEach() {
        session = JDBM.Hibernate.openSession()
    }

    @Test
    fun `create a role without permissions`() {
        val role = Role()

        val session = JDBM.Hibernate.openSession()
        JDBM.Hibernate.executeQuery(session) {
            session.save(role)
        }
    }

    @Test
    fun `given a resource and permissions, when insert role, then create a role -- should does not throw Exception`() {
//        Given
        val randomLocation = randomString("/test")
        val resource = Resource(resourceUUID = null, name = "test", location = "/test 12345")
        val permission1 = Permission(permissionUUID = null, operation = Operation.CREATE, resource = resource)
        val permission2 = Permission(permissionUUID = null, operation = Operation.READ, resource = resource)

//        When
        val permissions = listOf(
                permission1,
                permission2
        )
        val role = Role(subscription = Subscription(), permissions = permissions)

//        Assertions.assertEquals(false, hibernate.isUniquenessValid(resource))
        Assertions.assertEquals(false, hibernate.isUniquenessValid(role))
//        Assertions.assertEquals(true, hibernate.isUniquenessValid(role))


 /*       assertDoesNotThrow("") {
            if (hibernate.isUniquenessValid(role)) {
                JDBM.Hibernate.executeQuery(session) {
                    session.save(role)
                }
            }
        }

        Assertions.assertEquals(true, hibernate.isUniquenessValid(role))*/

    }


}