package hsenasilva.com.github.sample.cqrs.configuration

import com.mongodb.MongoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 * @author hsena
 */
@Configuration
@EnableReactiveMongoRepositories(basePackages = ["hsenasilva.com.github.sample.cqrs.repository"])
class MongoConfig {

    @Bean
    fun mongo() = MongoClient("localhost")

    @Bean
    fun mongoTemplate() = MongoTemplate(mongo(), "test")

}