package hsenasilva.com.github.sample.cqrs.aggregate

import hsenasilva.com.github.sample.cqrs.core.domain.ListedSampleEvent
import hsenasilva.com.github.sample.cqrs.core.domain.Sample
import hsenasilva.com.github.sample.cqrs.domain.ListSampleCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.stereotype.Component

/**
 * @author hsena
 */
@Component
@Aggregate
class SampleAggregate {

    @AggregateIdentifier
    private lateinit var requestId: String

    constructor()

    @CommandHandler
    constructor(command: ListSampleCommand) {
        apply(
                ListedSampleEvent(
                        sample = Sample(
                                requestId = command.listSampleParameter.requestId,
                                id = command.listSampleParameter.id,
                                stuff = command.listSampleParameter.stuff
                        )
                )
        )
    }

    @EventSourcingHandler
    fun on(event: ListedSampleEvent) {
        this.requestId = event.sample.requestId
    }

}