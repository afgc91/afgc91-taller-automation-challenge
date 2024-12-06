package tests

import models.User
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import steps.UsersSteps.Companion.getUsers
import steps.UsersSteps.Companion.getUsersByAllCitiesExcept
import steps.UsersSteps.Companion.getUsersByCities
import steps.UsersSteps.Companion.getUsersByCity
import steps.UsersSteps.Companion.getUsersByPartialCatchPhrase

class UsersTest {
    private lateinit var users: List<User>

    @Test
    fun `Get list of users`() {
        users= getUsers()

        assertTrue(users.size > 1, "The user list should have multiple users")
    }

    @Test
    fun `Get list of users filtered by city`() {
        val city = "Wisokyburgh"
        users = getUsersByCity(city)

        assertTrue(users.all { it.address.city.equals(city, ignoreCase = true) })
    }

    @Test
    fun `Get list of users filtered by cities`() {
        val cities = listOf("Gwenborough", "McKenziehaven")
        users = getUsersByCities(cities)

        assertTrue(users.all { cities.contains(it.address.city) })
    }

    @Test
    fun `Get list of users filtered by all cities excluding one city`() {
        val excludedCity = "Gwenborough"
        users = getUsersByAllCitiesExcept(excludedCity)

        assertTrue(users.all { it.address.city != "Gwenborough" })
    }

    @Test
    fun `Get list of users filtered by all cities excluding multiple cities`() {
        val excludedCities = listOf("Gwenborough", "Wisokyburgh")
        users = getUsersByAllCitiesExcept(excludedCities)

        assertTrue(users.all { !excludedCities.contains(it.address.city) })
    }

    @Test
    fun `Get list of users filtered by catch phrase (non-exact match)`() {
        val catchPhrase = "client-server"
        users = getUsersByPartialCatchPhrase(catchPhrase)

        assertTrue(users.all { it.company.catchPhrase.contains("client-server", ignoreCase = true) })
    }

    @Test
    fun `Get list of users filtered by non-existent catch phrase`() {
        val nonExistentCatchPhrase = "non-existent phrase"
        users = getUsersByPartialCatchPhrase(nonExistentCatchPhrase)

        assertTrue(users.isEmpty())
    }
}