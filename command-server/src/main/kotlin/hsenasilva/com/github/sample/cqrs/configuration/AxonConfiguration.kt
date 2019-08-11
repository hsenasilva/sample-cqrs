package hsenasilva.com.github.sample.cqrs.configuration

import com.mongodb.MongoClient
import hsenasilva.com.github.sample.cqrs.aggregate.SampleAggregate
import org.axonframework.commandhandling.CommandBus
import org.axonframework.commandhandling.CommandMessage
import org.axonframework.commandhandling.distributed.DistributedCommandBus
import org.axonframework.eventsourcing.AggregateFactory
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition
import org.axonframework.eventsourcing.GenericAggregateFactory
import org.axonframework.eventsourcing.Snapshotter
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.extensions.mongo.DefaultMongoTemplate
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.util.function.BiFunction


/**
 * @author hsena
 */
@Configuration
class AxonConfiguration {

    @Value("\${axon.snapshot.trigger.treshold.sample-events}")
    private val snapshotTriggerTreshold: Int = 100

    /* example to register an interceptor to add some metadata in all commands */
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

    @Bean
    fun snapshotterFactoryBean() = SpringAggregateSnapshotterFactoryBean()

    @Bean("sampleSnapshotTriggerDefinition")
    fun sampleSnapshotTriggerDefinition(snapshotter: Snapshotter) = EventCountSnapshotTriggerDefinition(snapshotter, snapshotTriggerTreshold)


    @Bean
    fun storageEngine(client: MongoClient): EventStorageEngine {
        return MongoEventStorageEngine.builder().mongoTemplate(DefaultMongoTemplate.builder().mongoDatabase(client).build()).build()
    }

    @Bean
    fun aggregateFactory(): AggregateFactory<SampleAggregate> {
        return GenericAggregateFactory(SampleAggregate::class.java)
    }

}
