package com.manideepla.bookerang

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource


@Configuration
class DataSourceConfig {

    @Autowired
    lateinit var dataSourceProperties: DataSourceProperties

    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.url = dataSourceProperties.url
        dataSource.username = dataSourceProperties.username
        dataSource.password = dataSourceProperties.password
        return dataSource
    }
}