package hsenasilva.com.github.sample.cqrs.aggregate

import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import hsenasilva.com.github.sample.cqrs.domain.ListedSampleEvent
import hsenasilva.com.github.sample.cqrs.domain.Sample
import hsenasilva.com.github.sample.cqrs.repository.SampleRepository
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author hsena
 */
@Component
@Aggregate(snapshotTriggerDefinition = "sampleSnapshotTriggerDefinition")
class SampleAggregate {

    @AggregateIdentifier
    private lateinit var requestId: String

    constructor()

    @CommandHandler
    constructor(command: ListSampleCommand) {
        apply(ListedSampleEvent(sample = Sample(requestId = command.listSampleParameter.requestId,
                id = command.listSampleParameter.id, stuff = command.listSampleParameter.stuff),
                listSampleParameter = command.listSampleParameter))
    }

    @EventSourcingHandler
    fun on(event: ListedSampleEvent, @Autowired repository: SampleRepository) {
        this.requestId = event.listSampleParameter.requestId

//        TODO - FIX DB STEP
//        event.sample?.also {
//            repository.findById(event.sample.id)?.let {
//                repository.save(event.sample.copy(it.requestId))
//            } ?: repository.save(event.sample)
//        }
    }

}