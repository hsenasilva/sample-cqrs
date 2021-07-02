package hsenasilva.com.github.sample.cqrs.aggregate

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.core.domain.CreditedBalance
import hsenasilva.com.github.sample.cqrs.core.domain.DebitedBalance
import hsenasilva.com.github.sample.cqrs.domain.CreditBalanceCommand
import hsenasilva.com.github.sample.cqrs.domain.DebitBalanceCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate
import java.math.BigDecimal

/**
 * @author hsena
 */
@Aggregate
class CheckingAccountAggregate {

    @AggregateIdentifier
    private lateinit var id: Account
    private var balance: BigDecimal = BigDecimal.ZERO

    constructor()

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: CreditBalanceCommand) {
        apply(
            CreditedBalance(
                account = command.id,
                value = command.value,
                balance = this.balance.plus(command.value)
            )
        )
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: DebitBalanceCommand) {
        if (command.value > BigDecimal.ZERO) {
            throw IllegalArgumentException("value >= 0")
        }
        apply(
            DebitedBalance(
                account = command.id,
                value = command.value,
                balance = this.balance.plus(command.value)
            )
        )
    }

    @EventSourcingHandler
    fun on(event: CreditedBalance) {
        this.id = event.id
        this.balance = event.balance
    }

    @EventSourcingHandler
    fun on(event: DebitedBalance) {
        this.id = event.id
        this.balance = event.balance
    }
}
