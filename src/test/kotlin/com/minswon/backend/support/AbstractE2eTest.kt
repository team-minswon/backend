package com.minswon.backend.support

import com.minswon.backend.support.mysql.MySQLContainerInitializer
import com.minswon.backend.support.mysql.MySQLDataCleanupExtension
import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.builder.ResponseSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = [MySQLContainerInitializer::class])
@ExtendWith(MySQLDataCleanupExtension::class)
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
abstract class AbstractE2eTest {

    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun beforeEach() {
        RestAssured.requestSpecification = RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setPort(port)
            .log(LogDetail.ALL)
            .build()
        RestAssured.responseSpecification = ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .build()
    }
}
