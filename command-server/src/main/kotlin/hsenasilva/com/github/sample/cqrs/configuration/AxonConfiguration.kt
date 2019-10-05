package hsenasilva.com.github.sample.cqrs.configuration

import com.mongodb.MongoClient
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.commandhandling.distributed.DistributedCommandBus
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.messaging.interceptors.BeanValidationInterceptor
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.util.function.BiFunction


/**
 * @author hsena
 */
@Configuration
class AxonConfiguration {

    /* useful example to register an interceptor to add some metadata in all commands */
    @Primary
    @Autowired
    fun registerInterceptors(distributedCommandBus: DistributedCommandBus) {
        registerDispatchInterceptor(distributedCommandBus)
    }

    private fun registerDispatchInterceptor(commandBus: CommandBus) {
        commandBus.registerDispatchInterceptor {
            BiFunction<Int, CommandMessage<*>, CommandMessage<*>> { _, message ->
                message.andMetaData(mapOf("tenant" to "sample_segment"))
            }
        }
    }

    @Autowired
    fun registerInterceptors(commandBus: CommandBus) {
        commandBus.registerDispatchInterceptor(BeanValidationInterceptor())
    }

    @Bean
    fun snapshotterFactoryBean() = SpringAggregateSnapshotterFactoryBean()

    @Bean
    fun storageEngine(client: MongoClient): EventStorageEngine {
        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build()
    }

}
