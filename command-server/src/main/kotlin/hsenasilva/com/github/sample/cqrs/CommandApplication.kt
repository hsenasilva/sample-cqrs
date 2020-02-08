package hsenasilva.com.github.sample.cqrs

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication


/**
 * @author hsena
 */
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class, KafkaAutoConfiguration::class])
class CommandApplication

fun main(args: Array<String>) {
    runApplication<CommandApplication>(*args)
}