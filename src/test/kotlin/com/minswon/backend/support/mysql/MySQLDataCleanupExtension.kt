package com.minswon.backend.support.mysql

import org.junit.jupiter.api.extension.BeforeEachCallback
import org.springframework.beans.factory.getBean
import org.springframework.test.context.junit.jupiter.SpringExtension

class MySQLDataCleanupExtension : BeforeEachCallback {

    override fun beforeEach(context: org.junit.jupiter.api.extension.ExtensionContext) {
        val cleaner = SpringExtension.getApplicationContext(context)
            .getBean<MySQLDataCleaner>()
        cleaner.clear()
    }
}
