package hsenasilva.com.github.sample.cqrs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


/**
 * @author hsena
 */
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class QueryApplication

fun main(args: Array<String>) {
    runApplication<QueryApplication>(*args)
}