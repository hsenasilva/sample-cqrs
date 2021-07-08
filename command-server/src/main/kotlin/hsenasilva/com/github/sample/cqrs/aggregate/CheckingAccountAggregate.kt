package hsenasilva.com.github.sample.cqrs.aggregate

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.core.domain.CreditedBalance
import hsenasilva.com.github.sample.cqrs.core.domain.DebitedBalance
import hsenasilva.com.github.sample.cqrs.domain.CheckingAccountEntryCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal

/**
 * @author hsena
 */
@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition", repository = "snapshotRepository")
class CheckingAccountAggregate {

    @AggregateIdentifier
    private lateinit var id: Account
    private var balance: BigDecimal = BigDecimal.ZERO

    constructor()

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: CheckingAccountEntryCommand) {
        if (command.value == BigDecimal.ZERO) {
            throw IllegalArgumentException("The balance entry parameter cannot be equal 0")
        }
        if (command.value > BigDecimal.ZERO) {
            AggregateLifecycle.apply(
                CreditedBalance(
                    account = command.id,
                    value = command.value,
                    balance = this.balance.plus(command.value)
                )
            )
        } else {
            AggregateLifecycle.apply(
                DebitedBalance(
                    account = command.id,
                    value = command.value,
                    balance = this.balance.plus(command.value)
                )
            )
        }
    }

    @EventSourcingHandler
    fun on(event: CreditedBalance) {
        this.id = event.id
        this.balance = event.balance
        logger.info(" ********  CreditedBalance ${event.balance}  ********  ")
    }

    @EventSourcingHandler
    fun on(event: DebitedBalance) {
        this.id = event.id
        this.balance = event.balance
        logger.info(" ********  DebitedBalance ${event.balance} ********  ")
    }

    private companion object {
        val logger: Logger = LoggerFactory.getLogger(CheckingAccountAggregate::class.java)
    }
}
