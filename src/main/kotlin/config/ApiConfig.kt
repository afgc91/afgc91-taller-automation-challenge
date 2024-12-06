package config

import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import java.util.*
import kotlin.properties.Delegates

object ApiConfig {
    const val USERS_PATH = "/users"

    const val OK = 200
    var PRINT_USER_LIST by Delegates.notNull<Boolean>()

    init {
        val props = Properties().apply {
            load(ApiConfig::class.java.getResourceAsStream("/config.properties"))
        }

        RestAssured.baseURI = props.getProperty("api.baseUri")
        PRINT_USER_LIST = props.getProperty("printUserList").toBoolean()
    }

    fun getRequestSpec(): RequestSpecification = RestAssured.given()
        .header("Content-Type", "application/json")
}