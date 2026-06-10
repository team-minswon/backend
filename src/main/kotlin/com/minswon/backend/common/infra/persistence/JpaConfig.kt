package com.minswon.backend.common.infra.persistence

import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing
class JpaConfig
