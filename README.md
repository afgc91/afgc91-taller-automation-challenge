# afgc91-taller-automation-challenge

## Project Structure:
*	afgc91-taller-automation-challenge/
  * main/
    * kotlin/
      * config/
        * ApiConfig.kt
      * models/
        * User.kt
      * utils/
    * resources/
      * config.properties
  * test/
    * kotlin/
      * steps/
        * UsersSteps.kt
      * tests/
        * UsersTest.kt
    * resources/

### main/
Package that stores the main code of the automation framework, tipically the business logic.

### main/kotlin/config/
This package is for the configuration of the automation framework. In this case, I used it for loading the properties from the file config.proterties, set the server base URL for Rest Assured and get the value of the flag that I created for printing the results in the console if it's true, otherwise they won't be printed. This logic is in the ApiConfig.kt class. This class also includes the response codes returned by the server we are testing. For this exercise I only used 200 OK that is the positive response of the GET /users API. The paths of the used APIs are also part of this class.

### main/kotlin/models/
Assuming that the APIs we are testing accepts and respond with JSON, this package is for the JSON mapping. We modelate the different objects that can be request payloads or responses. In this case I modeled the User.kt class that represents a single user. The GET /users API returns a list of users.

### main/kotlin/utils/
I did not use this package, but it should be used for some common functions used by all the classes in the project, like generation of random data (ids, names, numbers, etc.), formatting dates, evaluating string patterns, etc.

### main/resources/
This package is used for the configuration files. Is a good practice to have one config file for the common settings and multiple ones for the different environments in which the automated tests are needed to be run (f.e. QA, development, stage, production).

### test/
This package stores the code related to the tests, like test classes that contains the tests cases and test steps that can be reused by multiple test scenarios that share a common logic between them.

### test/kotlin/steps/
This package is used for the step classes. Those classes have functions that represents steps within the tests and these steps can be reused by different test cases and scenarios, in order to avoid repeating code.

### test/kotlin/tests/
Package that contains the test classes. The test classes have the different automated test cases.

### test/resources/
I did not use this package but can be used for some configs related to the tests. F.e. it can be used for configuring the connection to a database that contains test data.

## Technology stack
This automation framework uses the following tools:
* JDK platform used for compilation of the code. I used the JDK 20 version.
* Kotlin as programming language
* Jackson for JSON mapping. This is the version used in this project: com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2
* Rest Assured for setting, building and sending HTTP requests. This is the version used in the project: io.rest-assured:rest-assured:5.3.0
* JUnit for running the tests: org.junit.jupiter:junit-jupiter:5.10.0

## How to set up and run the tests
* Clone the repository.
* Make sure you have installed the JDK 20.
* Make sure you have installed kotlin.
* Make sure you have installed and configured gradlew in the system environment variables.
* Change the value of the printUserList property in the config.properties file depending if you want or not to print the list of users in each test case.
* "gradlew clean build" for cleaning and building the project.
* "gradlew test" for running the tests.
