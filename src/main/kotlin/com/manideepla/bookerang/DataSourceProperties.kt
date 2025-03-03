package com.manideepla.bookerang

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
class DataSourceProperties {
    lateinit var url: String
    lateinit var username: String
    lateinit var password: String
}