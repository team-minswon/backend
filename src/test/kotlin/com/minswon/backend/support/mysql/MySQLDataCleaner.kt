package com.minswon.backend.support.mysql

import org.springframework.jdbc.core.ConnectionCallback
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


@Component
class MySQLDataCleaner(
    private val jdbcTemplate: JdbcTemplate,
) {

    fun clear() {
        val tableNames = getTableNames()
            .filterNot { tableName ->
                tableName == "flyway_schema_history" ||
                    tableName.uppercase().startsWith("BATCH_")
            }

        if (tableNames.isEmpty()) {
            return
        }

        jdbcTemplate.execute(ConnectionCallback<Unit> { connection ->
            connection.createStatement().use { statement ->
                statement.execute("SET FOREIGN_KEY_CHECKS = 0")

                try {
                    tableNames.forEach { tableName ->
                        statement.execute("TRUNCATE TABLE ${quote(tableName)}")
                    }
                } finally {
                    statement.execute("SET FOREIGN_KEY_CHECKS = 1")
                }
            }
        })
    }

    private fun getTableNames(): List<String> {
        return jdbcTemplate.queryForList(
            """
            SELECT TABLE_NAME
            FROM INFORMATION_SCHEMA.TABLES
            WHERE TABLE_SCHEMA = DATABASE()
              AND TABLE_TYPE = 'BASE TABLE'
            """.trimIndent(),
            String::class.java
        )
    }

    private fun quote(identifier: String): String {
        return "`" + identifier.replace("`", "``") + "`"
    }
}
