package com.minswon.backend.support.mysql

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.stereotype.Component
import org.testcontainers.mysql.MySQLContainer

@Component
class MySQLContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {

    override fun initialize(context: ConfigurableApplicationContext) {
        val properties = mapOf<String, String>(
            "spring.datasource.url" to MYSQL_CONTAINER.jdbcUrl,
            "spring.datasource.username" to MYSQL_CONTAINER.username,
            "spring.datasource.password" to MYSQL_CONTAINER.password,
        )
        TestPropertyValues.of(properties).applyTo(context)
    }

    companion object {
        private val MYSQL_CONTAINER = MySQLContainer("mysql:8.0.36")
            .withCommand(
                "--character-set-server=utf8mb4",
                "--collation-server=utf8mb4_unicode_ci",
            )

        init {
            MYSQL_CONTAINER.start()
        }
    }
}
