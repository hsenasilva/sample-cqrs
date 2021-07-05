package hsenasilva.com.github.sample.cqrs.configuration

import com.mongodb.client.MongoClient
import org.axonframework.commandhandling.AsynchronousCommandBus
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.messaging.interceptors.CorrelationDataInterceptor
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author hsena
 */
@Configuration
class AxonConfiguration {

    @Bean
    fun commandBus(txManager: TransactionManager, axonConfiguration: AxonConfiguration): AsynchronousCommandBus {
        return AsynchronousCommandBus
            .builder()
            .transactionManager(txManager)
            .messageMonitor(axonConfiguration.messageMonitor(AsynchronousCommandBus::class.java, "commandBus"))
            .build()
            .also { commandBus ->
                commandBus.registerHandlerInterceptor(
                    CorrelationDataInterceptor(axonConfiguration.correlationDataProviders())
                )
            }
    }

    @Bean
    fun storageEngine(client: MongoClient): EventStorageEngine {
        return MongoEventStorageEngine
            .builder()
            .mongoTemplate(
                this.mongoTemplate(client)
            ).build()
    }

    private fun mongoTemplate(client: MongoClient): DefaultMongoTemplate {
        return DefaultMongoTemplate
            .builder()
            .mongoDatabase(client)
            .build()
    }
}
