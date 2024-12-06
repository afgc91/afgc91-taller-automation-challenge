package steps

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import config.ApiConfig.OK
import config.ApiConfig.PRINT_USER_LIST
import config.ApiConfig.USERS_PATH
import config.ApiConfig.getRequestSpec
import io.restassured.response.Response
import models.User
import org.junit.jupiter.api.Assertions.assertEquals

class UsersSteps {
    companion object {
        private lateinit var response: Response
        private lateinit var users: List<User>
        private lateinit var filteredUsers: List<User>

        fun getUsers(): List<User> {
            response = getRequestSpec()
                .get(USERS_PATH)

            assertEquals(OK, response.statusCode)

            val mapper = jacksonObjectMapper()
            val users: List<User> = mapper.readValue(response.body.asString())

            return users
        }

        fun getUsersByCity(city: String): List<User> {
            users = getUsers()
            filteredUsers = users.filter { it.address.city.equals(city, ignoreCase = true) }

            if (PRINT_USER_LIST) {
                println("Filtered Users by City ($city):")
                filteredUsers.forEach { println(it) }
            }

            return filteredUsers
        }

        fun getUsersByCities(cities: List<String>): List<User> {
            users = getUsers()
            filteredUsers = users.filter { user ->
                cities.any { city -> user.address.city.equals(city, ignoreCase = true) }
            }

            if (PRINT_USER_LIST) {
                println("Filtered Users by Cities ($cities):")
                filteredUsers.forEach { println(it) }
            }

            return filteredUsers
        }

        fun getUsersByAllCitiesExcept(excludedCity: String): List<User> {
            users = getUsers()
            filteredUsers = users.filter { user ->
                user.address.city.lowercase() != excludedCity.lowercase()
            }

            if (PRINT_USER_LIST) {
                println("Filtered Users excluding City ($excludedCity):")
                filteredUsers.forEach { println(it) }
            }

            return filteredUsers
        }

        fun getUsersByAllCitiesExcept(excludedCities: List<String>): List<User> {
            users = getUsers()
            filteredUsers = users.filter { user ->
                excludedCities.none { city -> user.address.city.equals(city, ignoreCase = true) }
            }

            if (PRINT_USER_LIST) {
                println("Filtered Users excluding Cities ($excludedCities):")
                filteredUsers.forEach { println(it) }
            }

            return filteredUsers
        }

        fun getUsersByPartialCatchPhrase(phrase: String): List<User> {
            users = getUsers()
            filteredUsers = users.filter { user ->
                user.company.catchPhrase.contains(phrase, ignoreCase = true)
            }

            if (PRINT_USER_LIST) {
                println("Filtered Users by Catch Phrase Contains ($phrase):")
                filteredUsers.forEach { println(it) }
            }

            return filteredUsers
        }
    }
}