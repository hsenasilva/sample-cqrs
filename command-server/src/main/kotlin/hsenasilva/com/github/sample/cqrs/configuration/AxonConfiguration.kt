package hsenasilva.com.github.sample.cqrs.configuration

import com.mongodb.MongoClient
import org.axonframework.commandhandling.AsynchronousCommandBus
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventhandling.saga.repository.MongoSagaStore
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.messaging.interceptors.BeanValidationInterceptor
import org.axonframework.messaging.interceptors.CorrelationDataInterceptor
import org.axonframework.modelling.saga.repository.SagaStore
import org.axonframework.spring.config.AxonConfiguration
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

    @Bean
    fun commandBus(txManager: TransactionManager, axonConfiguration: AxonConfiguration): AsynchronousCommandBus {
        val commandBus = AsynchronousCommandBus.builder()
                .transactionManager(txManager)
                .messageMonitor(axonConfiguration.messageMonitor(AsynchronousCommandBus::class.java, "commandBus"))
                .build()
        commandBus.registerHandlerInterceptor(CorrelationDataInterceptor(axonConfiguration.correlationDataProviders()))
        return commandBus
    }

    /* useful example to register an interceptor to add some metadata in all commands */
    @Primary
    @Autowired
    fun registerInterceptors(asynchronousCommandBus: AsynchronousCommandBus) {
        registerDispatchInterceptor(asynchronousCommandBus)
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

    @Bean
    fun sampleSagaStore(client: MongoClient): SagaStore<Any> {
        return MongoSagaStore.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build()
    }

}
