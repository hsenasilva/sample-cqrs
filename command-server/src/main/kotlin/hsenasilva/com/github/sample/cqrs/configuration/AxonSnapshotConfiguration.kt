package hsenasilva.com.github.sample.cqrs.configuration

import org.axonframework.common.transaction.TransactionManager
import org.axonframework.eventsourcing.AggregateFactory
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition
import org.axonframework.eventsourcing.EventSourcingRepository
import org.axonframework.eventsourcing.Snapshotter
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.messaging.annotation.ParameterResolverFactory
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotter
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.Executors

/**
 * @author hsena
 */
@Configuration
class AxonSnapshotConfiguration {

//    @Value("\${axon.snapshot.trigger.threshold.sample-events}")
//    private val snapshotTriggerThresholdEvent: Int? = null
//
//    @Bean
//    fun snapshotterFactoryBean() = SpringAggregateSnapshotterFactoryBean()
//
//    @Bean
//    fun snapshotTriggerDefinition(snapshotter: Snapshotter) =
//        EventCountSnapshotTriggerDefinition(snapshotter, this.snapshotTriggerThresholdEvent!!)
//
//    @Bean
//    fun snapshotter(
//        parameterResolverFactory: ParameterResolverFactory,
//        eventStore: EventStore,
//        transactionManager: TransactionManager
//    ): SpringAggregateSnapshotter {
//        return SpringAggregateSnapshotter.builder()
//            .eventStore(eventStore)
//            .parameterResolverFactory(parameterResolverFactory)
//            .executor(Executors.newSingleThreadExecutor())
//            .transactionManager(transactionManager)
//            .build()
//    }
//
//    @Bean
//    fun snapshotRepository(
//        snapshotter: Snapshotter,
//        parameterResolverFactory: ParameterResolverFactory,
//        eventStore: EventStore
//    ): EventSourcingRepository<CheckingAccountAggregate> {
//        return EventSourcingRepository
//            .builder(CheckingAccountAggregate::class.java)
//            .aggregateFactory(this.checkingAccountAggregateFactory())
//            .eventStore(eventStore)
//            .parameterResolverFactory(parameterResolverFactory)
//            .snapshotTriggerDefinition(EventCountSnapshotTriggerDefinition(snapshotter,
//                this.snapshotTriggerThresholdEvent!!))
//            .build()
//    }
//
//    @Bean
//    fun checkingAccountAggregateFactory(): AggregateFactory<CheckingAccountAggregate> =
//        SpringPrototypeAggregateFactory("checkingAccountAggregate")

}
