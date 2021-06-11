package hsenasilva.com.github.sample.cqrs.aggregate

import hsenasilva.com.github.sample.cqrs.core.domain.*
import hsenasilva.com.github.sample.cqrs.domain.CancelCreateSampleCommand
import hsenasilva.com.github.sample.cqrs.domain.CreateSampleCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

/**
 * @author hsena
 */
@Aggregate
class SampleAggregate {

    @AggregateIdentifier
    private lateinit var id: SampleId
    private var status: SampleStatus? = null

    constructor()

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: CreateSampleCommand) {
        apply(CreatedSampleEvent(
                sampleId = command.id,
                sample = Sample(
                        id = command.createSampleParameter.id.id,
                        stuff = command.createSampleParameter.stuff,
                        action = command.createSampleParameter.action,
                        status = SampleStatus.CREATED
                )
        ))
    }

    @CommandHandler
    fun handle(command: CancelCreateSampleCommand) {
        apply(CanceledCreateSampleEvent(
                sampleId = command.id,
                sample = Sample(
                        id = command.createSampleParameter.id.id,
                        stuff = command.createSampleParameter.stuff,
                        action = command.createSampleParameter.action,
                        status = SampleStatus.CANCELED
                )
        ))
    }

    @EventSourcingHandler
    fun on(event: CreatedSampleEvent) {
        this.id = event.id
        this.status = event.sample.status
    }

    @EventSourcingHandler
    fun on(event: RequestedSampleEvent) {
        this.id = event.id
        this.status = event.sample.status
    }

    @EventSourcingHandler
    fun on(event: CanceledCreateSampleEvent) {
        this.id = SampleId(event.sampleId.id)
        this.status = event.sample.status
    }

    private fun applyYourBusinessRulesHere(command: CreateSampleCommand) =  command.createSampleParameter.action

}