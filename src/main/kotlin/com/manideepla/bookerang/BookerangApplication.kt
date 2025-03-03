package com.manideepla.bookerang

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories

@EnableJdbcRepositories
@SpringBootApplication
class BookerangApplication

fun main(args: Array<String>) {
    runApplication<BookerangApplication>(*args)
}
