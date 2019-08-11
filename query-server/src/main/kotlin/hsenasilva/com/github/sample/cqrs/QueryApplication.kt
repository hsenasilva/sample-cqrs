package hsenasilva.com.github.sample.cqrs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient


/**
 * @author hsena
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class QueryApplication

fun main(args: Array<String>) {
    runApplication<QueryApplication>(*args)
}