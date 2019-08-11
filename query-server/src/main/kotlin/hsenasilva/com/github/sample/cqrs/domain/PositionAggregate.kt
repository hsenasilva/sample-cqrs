//package vc.com.rico.async.api.pensionfunds.domain
//
//import org.apache.commons.lang.builder.EqualsBuilder
//import org.apache.commons.lang.builder.HashCodeBuilder
//import org.apache.commons.lang.builder.ToStringBuilder
//import org.axonframework.commandhandling.CommandHandler
//import org.axonframework.eventsourcing.EventSourcingHandler
//import org.axonframework.modelling.command.AggregateIdentifier
//import org.axonframework.spring.stereotype.Aggregate
//import org.axonframework.modelling.command.AggregateLifecycle.apply
//import org.axonframework.modelling.command.TargetAggregateIdentifier
//
///**
// * @author hsilva
// */
//@Aggregate(snapshotTriggerDefinition = "positionSnapshotTriggerDefinition")
//internal class PositionAggregate {
//
//    @AggregateIdentifier
//    private lateinit var customer: Customer
//
//    constructor()
//
//    @CommandHandler
//    constructor(command: ListPositionCommand) {
//        apply(ListPositionEvent(command.customer, command.productType))
//    }
//
//    @EventSourcingHandler
//    fun on(event: ListPositionEvent) {
//        customer = event.customer
//        println("BATEU")
//    }
//
////    override fun toString(): String = ToStringBuilder.reflectionToString(this)
////
////    override fun equals(other: Any?): Boolean = EqualsBuilder.reflectionEquals(this, other)
////
////    override fun hashCode(): Int = HashCodeBuilder.reflectionHashCode(this)
//}