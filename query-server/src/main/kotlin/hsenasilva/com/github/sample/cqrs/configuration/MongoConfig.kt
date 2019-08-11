package hsenasilva.com.github.sample.cqrs.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

/**
 * @author hsena
 */
@Configuration
@EnableMongoRepositories(basePackages = ["hsenasilva.com.github.sample.cqrs.repository"])
class MongoConfig