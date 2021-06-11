package hsenasilva.com.github.sample.cqrs.aggregate

import hsenasilva.com.github.sample.cqrs.core.domain.Account
import hsenasilva.com.github.sample.cqrs.core.domain.CreatedCheckingAccount
import hsenasilva.com.github.sample.cqrs.core.domain.CreditedBalance
import hsenasilva.com.github.sample.cqrs.core.domain.DebitedBalance
import hsenasilva.com.github.sample.cqrs.domain.CreateCheckingAccountCommand
import hsenasilva.com.github.sample.cqrs.domain.CreditBalanceCommand
import hsenasilva.com.github.sample.cqrs.domain.DebitBalanceCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
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
    constructor(command: CreateCheckingAccountCommand) {
        apply(
            CreatedCheckingAccount(
                Account = command.id,
            )
        )
    }

    @CommandHandler
    fun handle(command: CreditBalanceCommand) {
        apply(
            CreditedBalance(
                Account = command.id,
                value = command.value
            )
        )
    }

    @CommandHandler
    fun handle(command: DebitBalanceCommand) {
        if (command.value < BigDecimal.ZERO)
            apply(
                DebitedBalance(
                    Account = command.id,
                    value = command.value
                )
            )
    }

    @EventSourcingHandler
    fun on(event: CreatedCheckingAccount) {
        this.id = event.id
    }

    @EventSourcingHandler
    fun on(event: CreditedBalance) {
        this.balance = this.balance.plus(event.value)
    }

    @EventSourcingHandler
    fun on(event: DebitedBalance) {
        this.balance = this.balance.plus(event.value)
    }
}
